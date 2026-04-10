package io.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.ProgressBar;
import io.github.kusoroadeolu.clique.configuration.ProgressBarConfiguration;
import io.github.kusoroadeolu.clique.spi.AnsiCode;
import io.github.kusoroadeolu.clique.style.StyleCode;


import java.util.Random;


public class MusicVisualizer {

    // ── Config ────────────────────────────────────────────────────────────────
    static final int BANDS       = 16;
    static final int BAR_LENGTH  = 28;
    static final int MAX_VAL     = 100;
    static final int TICK_MS     = 100;
    static final int DURATION_MS = 30_000;

    // Tokyo Night colors per band (bass → highs)
    static final String[] BAND_COLORS = {
            "tokyo_cyan",    "tokyo_cyan",    "*tokyo_cyan",   "tokyo_blue",
            "tokyo_blue",    "*tokyo_blue",   "tokyo_magenta", "tokyo_magenta",
            "*tokyo_magenta","tokyo_magenta", "tokyo_magenta", "*tokyo_blue",
            "tokyo_blue",    "*tokyo_cyan",   "tokyo_cyan",    "tokyo_cyan",
    };

    static final String[] BAND_LABELS = {
            " SUB ", " SUB ", " 60  ", " 125 ",
            " 250 ", " 500 ", " 1K  ", " 2K  ",
            " 4K  ", " 8K  ", " 12K ", " 16K ",
            " AIR ", " AIR ", " ──  ", " ──  "
    };

    // ANSI constants
    static final AnsiCode PURPLE = Clique.rgb(187, 154, 247);
    static final AnsiCode CYAN   = Clique.rgb(125, 207, 255);
    static final AnsiCode RESET = StyleCode.RESET;
    static final AnsiCode DIM = StyleCode.DIM;

    static final Random   rng      = new Random();
    static final double[] envelope = new double[BANDS];
    static final double[] target   = new double[BANDS];

    // Pre-built configs per band (reused each frame, only bars are rebuilt)
    static ProgressBarConfiguration[] configs;

    public static void main(String[] args) throws InterruptedException {
        Clique.registerTheme("tokyo-night");
        buildConfigs();
        printHeader();

        long start = System.currentTimeMillis();
        int  frame = 0;

        while (System.currentTimeMillis() - start < DURATION_MS) {
            if (frame % 6 == 0) generateBeat();
            updateEnvelopes();

            if (frame > 0) moveCursorUp();

            renderFrame();

            Thread.sleep(TICK_MS);
            frame++;
        }

        fadeOut();
        System.out.println("\n" + PURPLE + "  ▶  session ended  ◀" + RESET);
    }

    // ── Setup ─────────────────────────────────────────────────────────────────

    static void buildConfigs() {
        configs = new ProgressBarConfiguration[BANDS];
        for (int i = 0; i < BANDS; i++) {
            configs[i] = ProgressBarConfiguration.builder()
                    .length(BAR_LENGTH)
                    .complete('█')
                    .incomplete('░')
                    .format("[" + BAND_COLORS[i] + "]:bar[/]")
                    .build();
        }
    }

    // ── Beat simulation ───────────────────────────────────────────────────────

    static void generateBeat() {
        for (int i = 0; i < BANDS; i++) {
            double bass  = Math.exp(-0.08 * i);
            double mid   = 0.4 * Math.exp(-0.05 * Math.pow(i - 8, 2));
            double noise = 0.3 + 0.7 * rng.nextDouble();
            target[i] = Math.min(MAX_VAL, Math.max(5, (bass * 0.7 + mid + 0.15) * noise * MAX_VAL));
        }
        // Kick drum — slam the low bands
        if (rng.nextInt(4) == 0)
            for (int i = 0; i < 4; i++) target[i] = 90 + rng.nextInt(10);

        // Hi-hat — spike the highs
        if (rng.nextInt(6) == 0)
            for (int i = 12; i < BANDS; i++) target[i] = 70 + rng.nextInt(30);
    }

    static void updateEnvelopes() {
        for (int i = 0; i < BANDS; i++) {
            double rate = target[i] > envelope[i] ? 0.6 : 0.25; // fast attack, slow decay
            envelope[i] += (target[i] - envelope[i]) * rate;
        }
    }

    // ── Rendering ─────────────────────────────────────────────────────────────

    static void printHeader() {
        System.out.println();
        System.out.println(PURPLE + "  ██╗   ██╗██╗███████╗██╗   ██╗ █████╗ ██╗     " + RESET);
        System.out.println(PURPLE + "  ██║   ██║██║██╔════╝██║   ██║██╔══██╗██║     " + RESET);
        System.out.println(PURPLE + "  ╚██╗ ██╔╝██║╚════██║██║   ██║██╔══██║██║     " + RESET);
        System.out.println(PURPLE + "   ╚████╔╝ ██║███████║╚██████╔╝██║  ██║███████╗" + RESET);
        System.out.println(PURPLE + "    ╚═══╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝" + RESET);
        System.out.println(CYAN   + "              t e r m i n a l   e q "            + RESET);
        System.out.println(DIM    + "  ─────────────────────────────────────────────" + RESET);
        System.out.println();
    }

    static void renderFrame() {
        for (int i = 0; i < BANDS; i++) {
            int val = Math.max(1, Math.min(MAX_VAL, (int) Math.round(envelope[i])));

            // Fresh bar each frame — simple and reliable
            ProgressBar bar = Clique.progressBar(MAX_VAL, configs[i]);
            bar.tick(val);

            String rendered = bar.get();
            String label    = DIM + "│" + RESET + PURPLE + BAND_LABELS[i] + RESET + DIM + "│" + RESET;

            System.out.println("  " + label + rendered);
        }
        System.out.flush();
    }

    static void moveCursorUp() {
        System.out.print("\u001B[16A");
        System.out.flush();
    }

    // ── Fade out ──────────────────────────────────────────────────────────────

    static void fadeOut() throws InterruptedException {
        for (int step = 0; step < 20; step++) {
            for (int i = 0; i < BANDS; i++) envelope[i] *= 0.8;
            moveCursorUp();
            renderFrame();
            Thread.sleep(60);
        }
    }
}