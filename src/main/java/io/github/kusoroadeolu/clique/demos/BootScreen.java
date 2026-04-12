package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.ProgressBar;
import io.github.kusoroadeolu.clique.configuration.BoxConfiguration;
import io.github.kusoroadeolu.clique.configuration.BoxType;
import io.github.kusoroadeolu.clique.configuration.ProgressBarConfiguration;
import io.github.kusoroadeolu.clique.style.Ink;


import java.util.Random;

public class BootScreen {

    static final Random RNG = new Random();

    public static void main(String[] args) throws InterruptedException {
        Clique.registerTheme("tokyo-night");

        System.out.print("\033[2J\033[H\033[?25l");

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
            System.out.print("\033[?25h")));

        biosPhase();
        Thread.sleep(400);
        osHeader();
        Thread.sleep(300);
        bootSequence();
        Thread.sleep(400);
        warningLines();
        Thread.sleep(600);
        accessGranted();

        System.out.print("\033[?25h");
    }

    // ── Phase 1: BIOS-style hardware scan ─────────────────────────────────────

    static void biosPhase() throws InterruptedException {
        String[][] checks = {
            { "NEXUS BIOS v4.2.1", "*tokyo_cyan" },
            { "Copyright (C) 2089 Nexus Systems Corp.", "tokyo_fg" },
            { "", "" },
            { "CPU: NEXUS-X9 Quantum Core @ 12.4GHz", "*tokyo_white" },
            { "Checking CPU integrity...........[ OK ]", "tokyo_green" },
            { "RAM: 131072 MB DDR9 ECC", "*tokyo_white" },
            { "Memory test in progress.............", "*tokyo_white" },
        };

        for (String[] line : checks) {
            if (line[0].isEmpty()) { System.out.println(); continue; }
            typewrite(line[0], line[1], 18);
        }

        // Memory counter
        for (int i = 0; i <= 131072; i += 4096) {
            System.out.print("\r" + Clique.ink().rgb(180, 180, 210)
                .on(String.format("Memory test: %6d MB / 131072 MB", i)));
            Thread.sleep(60);
        }
        System.out.println(Clique.ink().rgb(100, 220, 140)
            .on("\rMemory test: 131072 MB / 131072 MB  [ OK ]"));

        Thread.sleep(200);
        String[][] checks2 = {
            { "Neural Interface Card: NX-7700........[ OK ]", "tokyo_green" },
            { "Biometric Scanner: ACTIVE.............[ OK ]", "tokyo_green" },
            { "Quantum Encryption Module: v9.1.......[ OK ]", "tokyo_green" },
            { "Network: NEXUS-NET 10Tb/s.............[ OK ]", "tokyo_green" },
            { "", "" },
            { "Booting NEXUS OS...", "*tokyo_cyan" },
        };

        for (String[] line : checks2) {
            if (line[0].isEmpty()) { System.out.println(); continue; }
            typewrite(line[0], line[1], 14);
        }
    }

    // ── Phase 2: OS header box ─────────────────────────────────────────────────

    static void osHeader() throws InterruptedException {
        Thread.sleep(300);
        System.out.println();


        Clique.box(BoxType.DOUBLE_LINE, Clique.rgb(115, 218, 202))
            .content(
                "[*tokyo_cyan, bold]███╗   ██╗███████╗██╗  ██╗██╗   ██╗███████╗[/]\n" +
                "[*tokyo_cyan, bold]████╗  ██║██╔════╝╚██╗██╔╝██║   ██║██╔════╝[/]\n" +
                "[*tokyo_cyan, bold]██╔██╗ ██║█████╗   ╚███╔╝ ██║   ██║███████╗[/]\n" +
                "[*tokyo_cyan, bold]██║╚██╗██║██╔══╝   ██╔██╗ ██║   ██║╚════██║[/]\n" +
                "[*tokyo_cyan, bold]██║ ╚████║███████╗██╔╝ ██╗╚██████╔╝███████║[/]\n" +
                "[*tokyo_cyan, bold]╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝[/]\n\n" +
                "[tokyo_fg]NEXUS OS  //  KERNEL 9.4.1-QUANTUM  //  SECURE BOOT ENABLED[/]"
            )
            .render();

        System.out.println();
    }

    // ── Phase 3: Boot progress bars ───────────────────────────────────────────

    static void bootSequence() throws InterruptedException {
        String[][] stages = {
            { "LOADING KERNEL MODULE       ", "100" },
            { "MOUNTING ENCRYPTED VOLUMES  ", "100" },
            { "INITIALIZING NEURAL NET     ", "100" },
            { "ESTABLISHING SECURE TUNNEL  ", "100" },
            { "DECRYPTING PARTITION TABLE  ", "100" },
            { "LOADING USER PROFILE        ", "100" },
            { "RUNNING THREAT ANALYSIS     ", "100" },
        };

        for (String[] stage : stages) {
            int total = Integer.parseInt(stage[1]);

            ProgressBarConfiguration config = ProgressBarConfiguration.builder()
                .length(36)
                .complete('█')
                .incomplete('░')
                .styleRange(0,  40,  "[tokyo_fg]" + stage[0] + "[/] [tokyo_blue]:bar[/] [tokyo_fg]:percent%[/]")
                .styleRange(40, 80,  "[tokyo_fg]" + stage[0] + "[/] [*tokyo_cyan]:bar[/] [tokyo_fg]:percent%[/]")
                .styleRange(80, 101, "[tokyo_fg]" + stage[0] + "[/] [tokyo_green]:bar[/] [tokyo_fg]:percent%[/]")
                .build();

            ProgressBar bar = Clique.progressBar(total, config);
            int speed = 2 + RNG.nextInt(4);

            while (!bar.isDone()) {
                bar.tickAnimated(speed);
                Thread.sleep(18 + RNG.nextInt(25));
            }
            System.out.println();
            Thread.sleep(80 + RNG.nextInt(120));
        }
    }

    // ── Phase 4: Warnings / tension ───────────────────────────────────────────

    static void warningLines() throws InterruptedException {
        System.out.println();
        String[][] warnings = {
            { "[WARN]  Anomalous signal detected on subnet 10.0.7.x — monitoring", "tokyo_yellow" },
            { "[WARN]  Neural sync latency elevated: 4.2ms (threshold: 5.0ms)", "tokyo_yellow" },
            { "[INFO]  Biometric scan complete — 3 identities verified", "*tokyo_white" },
            { "[INFO]  Quantum key exchange: SUCCESS (2048-qubit)", "*tokyo_white" },
            { "[WARN]  Intrusion attempt logged from NODE::FF:A9:03 — blocked", "tokyo_yellow" },
            { "[INFO]  All security checks passed", "tokyo_green" },
            { "[SYS ]  Preparing user environment...", "*tokyo_cyan" },
        };

        for (String[] w : warnings) {
            typewrite(w[0], w[1], 12);
            Thread.sleep(120 + RNG.nextInt(180));
        }
        System.out.println();
    }

    // ── Phase 5: ACCESS GRANTED ────────────────────────────────────────────────

    static void accessGranted() throws InterruptedException {
        Thread.sleep(300);

        typewrite("[ AUTHENTICATING IDENTITY... ]", "tokyo_red", 25);
        Thread.sleep(800);

        System.out.print("\033[A\033[2K");

        Ink green = Clique.ink().of("tokyo_green").bold();
        Ink white = Clique.ink().of("tokyo_white").bold(); // *tokyo_white - need to check if ink supports theme bright variants
        Ink fg = Clique.ink().of("tokyo_fg");

        String content =
                        green.on("  ██████╗ ██████╗  █████╗ ███╗   ██╗████████╗███████╗██████╗  ") + "\n" +
                        green.on("  ██╔════╝ ██╔══██╗██╔══██╗████╗  ██║╚══██╔══╝██╔════╝██╔══██╗ ") + "\n" +
                        green.on("  ██║  ███╗██████╔╝███████║██╔██╗ ██║   ██║   █████╗  ██║  ██║ ") + "\n" +
                        green.on("  ██║   ██║██╔══██╗██╔══██║██║╚██╗██║   ██║   ██╔══╝  ██║  ██║ ") + "\n" +
                        green.on("  ╚██████╔╝██║  ██║██║  ██║██║ ╚████║   ██║   ███████╗██████╔╝ ") + "\n" +
                        green.on("   ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚═════╝  ") + "\n\n" +
                        white.on("          IDENTITY CONFIRMED  //  CLEARANCE LEVEL: OMEGA         ") + "\n" +
                        fg.on("              Welcome back, Agent. NEXUS is ready.              ");

        Clique.box(BoxType.DOUBLE_LINE, Clique.rgb(158, 206, 106))
            .content(content)
            .render();

        System.out.println();
    }

    // ── Utility: typewriter effect ─────────────────────────────────────────────

    static void typewrite(String text, String color, int delayMs) throws InterruptedException {
        String styled = Clique.ink().of(color).on(text);
        for (char ch : styled.toCharArray()) {
            System.out.print(ch);
            Thread.sleep(delayMs);
        }
        System.out.println();
    }
}