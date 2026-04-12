package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Component;
import io.github.kusoroadeolu.clique.configuration.CellAlign;
import io.github.kusoroadeolu.clique.configuration.FrameAlign;
import io.github.kusoroadeolu.clique.configuration.TableConfiguration;
import io.github.kusoroadeolu.clique.configuration.TableType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public final class LiveDashboard {

    // ─── Config ───────────────────────────────────────────────────────────────

    private static final int    TICKS        = 60;
    private static final int    TICK_MS      = 500;
    private static final int    CHART_WIDTH  = 30;
    private static final Random RNG          = new Random();

    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    // ─── State ────────────────────────────────────────────────────────────────

    // Sparkline history windows
    private static final Deque<Double> cpuHistory = new ArrayDeque<>();
    private static final Deque<Double> memHistory = new ArrayDeque<>();
    private static final Deque<Double> reqHistory = new ArrayDeque<>();

    // Simulated base values that drift over time
    private static double cpuBase  = 28.0;
    private static double memBase  = 54.0;
    private static double reqBase  = 820.0;

    // Service states — one degrades at tick 20, recovers at tick 40
    private static final String[] SERVICE_NAMES = {
            "api-gateway", "auth-service", "user-service", "db-primary", "cache"
    };
    private static final double[] SERVICE_UPTIME = { 99.98, 99.91, 99.87, 99.99, 99.95 };

    private static int tick = 0;

    // Cursor tracking (same trick as GeminiThoughtStream)
    private static int lastLines = 0;

    // ─── Entry point ─────────────────────────────────────────────────────────

    public static void main(String[] args) throws InterruptedException {
        // Seed history so charts aren't empty on first render
        for (int i = 0; i < CHART_WIDTH; i++) {
            cpuHistory.addLast(simulate(cpuBase, 8));
            memHistory.addLast(simulate(memBase, 5));
            reqHistory.addLast(simulate(reqBase, 120));
        }

        System.out.println();

        for (int i = 0; i < TICKS; i++) {
            tick = i;
            update();
            render();
            Thread.sleep(TICK_MS);
        }

        // Final newline so the prompt appears cleanly after
        System.out.println();
    }

    // ─── Simulation ──────────────────────────────────────────────────────────

    private static void update() {
        // Drift base values slowly
        cpuBase  = clamp(cpuBase  + RNG.nextGaussian() * 2.5, 5,   95);
        memBase  = clamp(memBase  + RNG.nextGaussian() * 1.2, 20,  90);
        reqBase  = clamp(reqBase  + RNG.nextGaussian() * 40,  200, 2000);

        // Spike CPU at tick 25 to make charts interesting
        double cpuVal = tick == 25 ? clamp(cpuBase + 35, 0, 100)
                                   : simulate(cpuBase, 6);
        double memVal = simulate(memBase, 4);
        double reqVal = simulate(reqBase, 80);

        push(cpuHistory, cpuVal);
        push(memHistory, memVal);
        push(reqHistory, reqVal);
    }

    private static double simulate(double base, double noise) {
        return clamp(base + RNG.nextGaussian() * noise, 0, 100);
    }

    private static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }

    private static void push(Deque<Double> deque, double value) {
        if (deque.size() >= CHART_WIDTH) deque.pollFirst();
        deque.addLast(value);
    }

    // ─── Rendering ───────────────────────────────────────────────────────────

    private static void render() {
        // Build each section as a string
        String header   = buildHeader();
        String services = buildServices();
        String charts   = buildCharts();
        String footer   = buildFooter();

        String output = header + "\n" + services + "\n" + charts + "\n" + footer;
        List<String> lines = output.lines().toList();

        // Erase previous frame
        if (lastLines > 0) {
            System.out.print("\033[" + lastLines + "A");
            System.out.print("\033[J");
        }
        lastLines = lines.size();

        for (String line : lines) {
            System.out.println("\r" + line);
        }
    }

    // ─── Header ──────────────────────────────────────────────────────────────

    private static String buildHeader() {
        io.github.kusoroadeolu.clique.components.Component clock = () -> {
            String time   = LocalTime.now().format(TIME_FMT);
            long   uptime = (long) tick * TICK_MS / 1000;
            long   mins   = uptime / 60;
            long   secs   = uptime % 60;

            return Clique.table(TableType.BOX_DRAW, TableConfiguration.builder()
                    .borderColor("blue")
                    .alignment(CellAlign.CENTER)
                    .padding(1)
                    .build())
                    .headers(
                            "[*blue, bold]⬡ SYSTEM MONITOR[/]",
                            "[dim]time[/]  [*white]" + time + "[/]",
                            "[dim]uptime[/]  [*white]" + String.format("%02d:%02d", mins, secs) + "[/]",
                            "[dim]tick[/]  [*white]" + (tick + 1) + "/" + TICKS + "[/]"
                    )
                    .get();
        };

        return Clique.frame("blue")
                .nest(clock)
                .get();
    }

    // ─── Services table ───────────────────────────────────────────────────────

    private static String buildServices() {
        io.github.kusoroadeolu.clique.components.Component table = () -> {
            var t = Clique.table(TableType.BOX_DRAW, TableConfiguration.builder()
                    .borderColor("cyan")
                    .columnAlignment(0, CellAlign.LEFT)
                    .columnAlignment(1, CellAlign.CENTER)
                    .columnAlignment(2, CellAlign.CENTER)
                    .columnAlignment(3, CellAlign.RIGHT)
                    .padding(1)
                    .build())
                    .headers(
                            "[*cyan, bold]Service[/]",
                            "[*cyan, bold]Status[/]",
                            "[*cyan, bold]Uptime[/]",
                            "[*cyan, bold]Latency[/]"
                    );

            for (int i = 0; i < SERVICE_NAMES.length; i++) {
                String name    = SERVICE_NAMES[i];
                double uptime  = SERVICE_UPTIME[i];
                int    latency = (int) simulate(40, 15);

                // auth-service degrades between ticks 20–40
                boolean degraded = i == 1 && tick >= 20 && tick < 40;
                // db-primary has a blip at tick 35
                boolean down     = i == 3 && tick == 35;

                String statusStr, uptimeStr, latencyStr;

                if (down) {
                    statusStr  = "[*red]● DOWN[/]";
                    uptimeStr  = "[*red]0.00%[/]";
                    latencyStr = "[*red]timeout[/]";
                } else if (degraded) {
                    statusStr  = "[yellow]◐ DEGRADED[/]";
                    uptimeStr  = "[yellow]" + String.format("%.2f%%", uptime - 0.4) + "[/]";
                    latencyStr = "[yellow]" + (latency + 120) + "ms[/]";
                } else {
                    statusStr  = "[*green]● RUNNING[/]";
                    uptimeStr  = "[*green]" + String.format("%.2f%%", uptime) + "[/]";
                    latencyStr = latency < 60
                            ? "[*green]" + latency + "ms[/]"
                            : "[yellow]"  + latency + "ms[/]";
                }

                t.row("[white]" + name + "[/]", statusStr, uptimeStr, latencyStr);
            }

            return t.get();
        };

        return Clique.frame("cyan")
                .title("[*cyan, bold] Services [/]", FrameAlign.LEFT)
                .nest(table)
                .get();
    }

    // ─── Sparkline charts ─────────────────────────────────────────────────────

    private static String buildCharts() {
        Component charts = () -> {
            String cpu = sparkline("CPU",      cpuHistory, 100, "red",   "green");
            String mem = sparkline("MEM",      memHistory, 100, "blue",  "cyan");
            String req = sparkline("REQ/s",    reqHistory, 2000, "magenta", "magenta");

            return cpu + "\n" + mem + "\n" + req;
        };

        return Clique.frame("magenta")
                .title("[*magenta, bold] Live Metrics [/]", FrameAlign.LEFT)
                .nest(charts)
                .get();
    }

    private static String sparkline(
            String label,
            Deque<Double> history,
            double max,
            String highColor,
            String lowColor
    ) {
        char[] blocks = { '▁', '▂', '▃', '▄', '▅', '▆', '▇', '█' };

        StringBuilder bar = new StringBuilder();
        double current = 0;

        for (double v : history) {
            int idx = (int) Math.round((v / max) * (blocks.length - 1));
            idx = Math.max(0, Math.min(blocks.length - 1, idx));

            // High values get the warning color
            String color = (v / max) > 0.7 ? highColor : lowColor;
            bar.append("[").append(color).append("]")
               .append(blocks[idx])
               .append("[/]");

            current = v;
        }

        // Format the current value label differently for req/s
        String valueStr = max == 2000
                ? String.format("%4.0f req/s", current)
                : String.format("%5.1f%%", current);

        String colorStr = (current / max) > 0.7
                ? "[*" + highColor + ", bold]"
                : "[dim]";

        return "  [dim]" + String.format("%-6s", label) + "[/]  "
                + Clique.parser().parse(bar.toString())
                + "  " + Clique.parser().parse(colorStr + valueStr + "[/]");
    }

    // ─── Footer ───────────────────────────────────────────────────────────────

    private static String buildFooter() {
        String time = LocalTime.now().format(TIME_FMT);
        return Clique.parser().parse(
                "  [dim]Last refresh: " + time
                + "   ·   interval: " + TICK_MS + "ms"
                + "   ·   ctrl+c to exit[/]"
        );
    }
}