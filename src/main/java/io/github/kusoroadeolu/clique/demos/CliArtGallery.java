package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Table;
import io.github.kusoroadeolu.clique.configuration.*;
import io.github.kusoroadeolu.clique.style.*;

public final class CliArtGallery {

    public static void main(String[] args) throws InterruptedException {
        displayGalleryEntrance();

        pause();
        exhibit1_RainbowText();

        pause();
        exhibit2_ColorPalette();

        pause();
        exhibit3_Tables();

        pause();
        exhibit4_Boxes();

        pause();
        exhibit5_Frames();

        pause();
        exhibit6_ProgressBars();

        pause();
        exhibit7_ThemeShowcase();

        pause();
        displayGalleryExit();
    }

    // ─── Entrance / Exit ──────────────────────────────────────────────────────

    private static void displayGalleryEntrance() {
        clearScreen();
        Clique.parser().print("\n\n");

        // BorderColor.of(ColorCode.BRIGHT_CYAN) → AnsiCode varargs overload on facade
        // autosize() removed — boxes autosize by default now
        Clique.box(BoxType.DOUBLE_LINE, ColorCode.BRIGHT_CYAN)
                .content("[*magenta, bold]  CLI ART GALLERY  [/]\n\n[dim]Where Code Meets Creativity[/]")
                .render();

        Clique.parser().print("\n           [yellow]Press Enter to begin tour...[/]");
    }

    private static void displayGalleryExit() {
        clearScreen();
        Clique.parser().print("\n\n");

        Clique.box(BoxType.DOUBLE_LINE, ColorCode.BRIGHT_MAGENTA)
                .content("[*cyan, bold]  Thank You For Visiting!  [/]\n\n[*white, italic]\"Make your terminal beautiful with [*magenta, bold]Clique[/][*white, italic]\"[/]")
                .render();

        System.out.println();
        Clique.parser().print("             [red]◆[/] [yellow]◆[/] [green]◆[/] [cyan]◆[/] [blue]◆[/] [magenta]◆[/]");
        Clique.parser().print("\n\n");
    }

    // ─── Exhibits ─────────────────────────────────────────────────────────────

    private static void exhibit1_RainbowText() {
        clearScreen();
        exhibitHeader("Exhibit 1", "Rainbow Typography");

        Clique.parser().print("\n");
        Clique.parser().print("     [*red, bold]██████[/]  [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██████[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██████[/]");
        Clique.parser().print("     [*red, bold]██[/]      [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██[/]  [*blue, bold]██[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██[/]");
        Clique.parser().print("     [*red, bold]██[/]      [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██[/]  [*blue, bold]██[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██████[/]");
        Clique.parser().print("     [*red, bold]██[/]      [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██[/]  [*blue, bold]██[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██[/]");
        Clique.parser().print("     [*red, bold]██████[/]  [*yellow, bold]██████[/]  [*green, bold]██[/]  [*blue, bold]██████[/]  [*magenta, bold]██████[/] [*white, bold]██████[/]");
        System.out.println("\n");

        String message = "Make Your CLI Beautiful";
        ColorCode[] rainbow = {
                ColorCode.RED, ColorCode.BRIGHT_RED, ColorCode.YELLOW, ColorCode.BRIGHT_YELLOW,
                ColorCode.GREEN, ColorCode.BRIGHT_GREEN, ColorCode.CYAN, ColorCode.BRIGHT_CYAN,
                ColorCode.BLUE, ColorCode.BRIGHT_BLUE, ColorCode.MAGENTA, ColorCode.BRIGHT_MAGENTA
        };

        System.out.print("     ");
        StyleBuilder sb = Clique.styleBuilder();
        for (int i = 0; i < message.length(); i++) {
            sb.append(String.valueOf(message.charAt(i)), rainbow[i % rainbow.length], StyleCode.BOLD);
        }
        System.out.println(sb);

        System.out.println();
        Clique.parser().print("     [red]█[/][*red]█[/][yellow]█[/][*yellow]█[/][green]█[/][*green]█[/][cyan]█[/][*cyan]█[/][blue]█[/][*blue]█[/][magenta]█[/][*magenta]█[/] [dim]Color Spectrum[/]");

        exhibitFooter("Markup parsing + StyleBuilder chaining");
    }

    private static void exhibit2_ColorPalette() {
        clearScreen();
        exhibitHeader("Exhibit 2", "Color Palette");

        Clique.parser().print("  [bold]Standard Colors:[/]");
        Clique.parser().print("  [black]■ Black  [/] [red]■ Red    [/] [green]■ Green  [/] [yellow]■ Yellow [/]");
        Clique.parser().print("  [blue]■ Blue   [/] [magenta]■ Magenta[/] [cyan]■ Cyan   [/] [white]■ White  [/]");
        System.out.println();

        Clique.parser().print("  [bold]Bright Colors:[/]");
        Clique.parser().print("  [*black]■ Black  [/] [*red]■ Red    [/] [*green]■ Green  [/] [*yellow]■ Yellow [/]");
        Clique.parser().print("  [*blue]■ Blue   [/] [*magenta]■ Magenta[/] [*cyan]■ Cyan   [/] [*white]■ White  [/]");
        System.out.println();

        Clique.parser().print("  [bold]Background Colors:[/]");
        Clique.parser().print("  [bg_red, white] RED [/] [bg_green, black] GREEN [/] [bg_blue, white] BLUE [/] [bg_yellow, black] YELLOW [/]");
        Clique.parser().print("  [bg_magenta, white] MAGENTA [/] [bg_cyan, black] CYAN [/] [*bg_red, white] BRIGHT RED [/]");
        System.out.println();

        Clique.parser().print("  [bold]Style Combinations:[/]");
        Clique.parser().print("  [red, bold]Bold[/]  [green, dim]Dim[/]  [blue, italic]Italic[/]  [yellow, ul]Underline[/]  [magenta, rv]Reverse[/]  [cyan, strike]Strike[/]");
        System.out.println();

        Clique.parser().print("  [bold]Color Blocks:[/]");
        Clique.parser().print("  [bg_red]    [/][bg_*red]    [/][bg_yellow]    [/][bg_*yellow]    [/][bg_green]    [/][bg_*green]    [/][bg_cyan]    [/][bg_*cyan]    [/]");
        Clique.parser().print("  [bg_blue]    [/][bg_*blue]   [/][bg_magenta]    [/][bg_*magenta]    [/][bg_white]    [/][bg_*white]    [/][bg_black]    [/][bg_*black]    [/]");

        exhibitFooter("All 32 ANSI colors + text styles");
    }

    private static void exhibit3_Tables() {
        clearScreen();
        exhibitHeader("Exhibit 3", "Tables");

        Clique.parser().print("  [bold]Leaderboard:[/]\n");

        // borderStyle(BorderColor.of("cyan")) → borderColor("cyan")
        TableConfiguration leaderConfig = TableConfiguration.builder()
                .borderColor("cyan")
                .alignment(CellAlign.CENTER)
                .padding(1)
                .build();

        Clique.table(TableType.ROUNDED_BOX_DRAW, leaderConfig)
                .headers("[*cyan, bold]Rank[/]", "[*cyan, bold]Player[/]", "[*cyan, bold]Score[/]", "[*cyan, bold]Status[/]")
                .row("[*yellow, bold]🥇 1[/]", "[*white, bold]Alice[/]",   "[*green]9850[/]",  "[*green]● Online[/]")
                .row("[*white]🥈 2[/]",        "[*white]Bob[/]",           "[yellow]7200[/]",  "[*green]● Online[/]")
                .row("[yellow]🥉 3[/]",        "Charlie",                  "[yellow]6100[/]",  "[dim]○ Away[/]")
                .row("[dim]4[/]",              "[dim]Diana[/]",            "[dim]4400[/]",     "[dim]○ Offline[/]")
                .render();

        System.out.println();

        Clique.parser().print("  [bold]Dependency Report:[/]\n");

        TableConfiguration depConfig = TableConfiguration.builder()
                .borderColor("blue")
                .columnAlignment(0, CellAlign.LEFT)
                .columnAlignment(1, CellAlign.CENTER)
                .columnAlignment(2, CellAlign.CENTER)
                .columnAlignment(3, CellAlign.RIGHT)
                .padding(1)
                .build();

        Clique.table(TableType.BOX_DRAW, depConfig)
                .headers("[*blue, bold]Package[/]", "[*blue, bold]Version[/]", "[*blue, bold]Status[/]", "[*blue, bold]Size[/]")
                .row("[white]clique-core[/]",   "[*green]3.2.2[/]",   "[*green]up to date[/]",  "42 KB")
                .row("[white]clique-themes[/]", "[*green]1.0.0[/]",   "[*green]up to date[/]",  "18 KB")
                .row("[white]jackson-core[/]",  "[yellow]2.14.0[/]",  "[yellow]outdated[/]",    "128 KB")
                .row("[white]slf4j-api[/]",     "[*green]2.0.9[/]",   "[*green]up to date[/]",  "64 KB")
                .render();

        exhibitFooter("Multiple table types + column alignment");
    }

    private static void exhibit4_Boxes() {
        clearScreen();
        exhibitHeader("Exhibit 4", "Boxes");

        // BorderColor.of("red") → String overload on facade
        // autosize() removed — now the default behavior
        Clique.box(BoxType.DOUBLE_LINE, "red")
                .content("[*red, bold]⚠  ALERT[/]\n\nSystem maintenance scheduled for tonight.\nExpected downtime: [bold]2 hours[/].")
                .render();

        System.out.println();

        Clique.box(BoxType.ROUNDED, "blue")
                .content("[*blue, bold]ℹ  INFO[/]\n\n[dim]This feature is currently in beta.\nPlease report any issues you encounter.[/]")
                .render();

        System.out.println();

        Clique.box(BoxType.ROUNDED, "green")
                .content("[*green, bold]✓  BUILD SUCCESSFUL[/]\n\n[dim]Compiled 42 files · 108 tests passed · 0 warnings[/]")
                .render();

        exhibitFooter("Box types + markup content + autosize");
    }

    private static void exhibit5_Frames() throws InterruptedException {
        clearScreen();
        exhibitHeader("Exhibit 5", "Frames");

        // borderStyle(BorderColor.of(...)) → borderColor(...) on config builder
        Table statusTable = Clique.table(TableType.BOX_DRAW, TableConfiguration.builder()
                        .borderColor("blue")
                        .padding(1)
                        .build())
                .headers("[*blue, bold]Service[/]", "[*blue, bold]Status[/]", "[*blue, bold]Uptime[/]")
                .row("[white]API Server[/]",    "[*green]● Running[/]",  "99.9%")
                .row("[white]Database[/]",      "[*green]● Running[/]",  "99.7%")
                .row("[white]Cache[/]",         "[yellow]● Degraded[/]", "98.1%")
                .row("[white]Worker Queue[/]",  "[*red]● Down[/]",       "0.0%");

        Table metricsTable = Clique.table(TableType.BOX_DRAW, TableConfiguration.builder()
                        .borderColor("cyan")
                        .alignment(CellAlign.CENTER)
                        .padding(1)
                        .build())
                .headers("[*cyan, bold]CPU[/]", "[*cyan, bold]Memory[/]", "[*cyan, bold]Requests/s[/]", "[*cyan, bold]Errors[/]")
                .row("[*green]23%[/]", "[yellow]67%[/]", "[*white]1,204[/]", "[*red]12[/]");

        // BorderColor.of("magenta") → String overload on facade
        Clique.frame("magenta")
                .title("[*magenta, bold] System Dashboard [/]")
                .nest("[*white, bold]Service Health[/]", FrameAlign.LEFT)
                .nest(statusTable)
                .nest("[*white, bold]Live Metrics[/]", FrameAlign.LEFT)
                .nest(metricsTable)
                .nest("[dim]Last updated: just now[/]", FrameAlign.RIGHT)
                .render();

        exhibitFooter("Frames nesting tables + strings + alignment");
    }

    private static void exhibit6_ProgressBars() throws InterruptedException {
        clearScreen();
        exhibitHeader("Exhibit 6", "Progress Bars");

        String[] labels = {"Blocks ", "Lines  ", "Bold   ", "Classic", "Dots   "};
        ProgressBarPreset[] presets = {
                ProgressBarPreset.BLOCKS,
                ProgressBarPreset.LINES,
                ProgressBarPreset.BOLD,
                ProgressBarPreset.CLASSIC,
                ProgressBarPreset.DOTS
        };

        System.out.println();
        for (int p = 0; p < presets.length; p++) {
            System.out.print("  [" + labels[p] + "] ");
            var bar = Clique.progressBar(40, presets[p]);
            for (int i = 0; i < 40; i++) {
                bar.tick();
                Thread.sleep(18);
            }
            System.out.println();
        }

        System.out.println();

        Clique.parser().print("  [bold]Dynamic Styling:[/]");
        System.out.println();
        System.out.print("  ");

        ProgressBarConfiguration dynamicConfig = ProgressBarConfiguration.builder()
                .styleRange(0,  34, "[red]:bar[/]    [red]:percent%[/]  Starting...")
                .styleRange(34, 67, "[yellow]:bar[/] [yellow]:percent%[/]  In Progress...")
                .styleRange(67, 100, "[*green]:bar[/] [*green]:percent%[/]  Almost Done!")
                .build();

        var dynamicBar = Clique.progressBar(60, dynamicConfig);
        dynamicBar.tickAnimated(60);

        System.out.println();

        exhibitFooter("All 5 presets + dynamic range styling");
    }

    private static void exhibit7_ThemeShowcase() {
        clearScreen();
        exhibitHeader("Exhibit 7", "Theme Gallery");

        // registerAllThemes() → registerAvailableThemes()
        Clique.registerAvailableThemes();

        Clique.parser().print("  [bold, ul]Catppuccin Mocha[/] [dim]— Soothing pastel dark[/]");
        Clique.parser().print("  [ctp_mauve]●[/] [ctp_pink]●[/] [ctp_red]●[/] [ctp_peach]●[/] [ctp_yellow]●[/] [ctp_green]●[/] [ctp_blue]●[/] [ctp_lavender]●[/]");
        Clique.parser().print("  [bg_ctp_surface0, ctp_text] UI Box [/]  [ctp_green]✓ Success[/]  [ctp_red]✗ Error[/]  [ctp_yellow]⚠ Warn[/]");
        System.out.println();

        Clique.parser().print("  [bold, ul]Dracula[/] [dim]— Iconic purple-accented dark[/]");
        Clique.parser().print("  [drac_red]●[/] [drac_green]●[/] [drac_yellow]●[/] [drac_blue]●[/] [drac_magenta]●[/] [drac_cyan]●[/]");
        Clique.parser().print("  [drac_magenta]♦[/] [*drac_white, bold]Dracula[/]  [drac_green]✓[/]  [drac_red]✗[/]  [drac_yellow]⚠[/]");
        System.out.println();

        Clique.parser().print("  [bold, ul]Gruvbox Dark[/] [dim]— Retro warm earthy tones[/]");
        Clique.parser().print("  [gb_red]●[/] [gb_orange]●[/] [gb_yellow]●[/] [gb_green]●[/] [gb_aqua]●[/] [gb_blue]●[/] [gb_purple]●[/]");
        Clique.parser().print("  [gb_orange, bold]⚡[/] [bg_gb_bg1, gb_aqua] Info [/]  [gb_fg]Retro vibes[/]");
        System.out.println();

        Clique.parser().print("  [bold, ul]Nord[/] [dim]— Cool arctic-inspired[/]");
        Clique.parser().print("  [nord_frost0]●[/] [nord_frost1]●[/] [nord_frost2]●[/] [nord_frost3]●[/] [nord_red]●[/] [nord_yellow]●[/] [nord_green]●[/]");
        Clique.parser().print("  [nord_frost2]❄[/] [nord_snow, bold]Nordic[/]  [nord_green]✓[/]  [nord_red]✗[/]  [nord_yellow]⚠[/]");
        System.out.println();

        Clique.parser().print("  [bold, ul]Tokyo Night[/] [dim]— Modern purple-blue dark[/]");
        Clique.parser().print("  [tokyo_red]●[/] [tokyo_green]●[/] [tokyo_yellow]●[/] [tokyo_blue]●[/] [tokyo_magenta]●[/] [tokyo_cyan]●[/]");
        Clique.parser().print("  [tokyo_magenta]◆[/] [*tokyo_white, bold]Tokyo[/]  [tokyo_cyan]›[/] [tokyo_fg]Modern & clean[/]");
        System.out.println();

        Clique.parser().print("  [bold, ul]Mix & Match[/] [dim]— Combine freely[/]");
        Clique.parser().print("  [ctp_mauve]Catppuccin[/] + [tokyo_cyan]Tokyo Night[/] + [nord_frost2]Nord[/] + [drac_magenta]Dracula[/] + [gb_orange]Gruvbox[/]");

        exhibitFooter("5 built-in themes + free mixing");
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    private static void exhibitHeader(String number, String title) {
        Clique.parser().print("\n  [*yellow, bold]" + number + "[/] [dim]│[/] [*white, bold]" + title + "[/]");
        Clique.parser().print("  [dim]" + "─".repeat(50) + "[/]\n");
    }

    private static void exhibitFooter(String technique) {
        System.out.println();
        Clique.parser().print("  [dim]Technique: " + technique + "[/]");
        Clique.parser().print("\n  [yellow]Press Enter to continue...[/]");
    }

    private static void pause() {
        try {
            do System.in.read();
            while (System.in.available() > 0);
        } catch (Exception e) {
            // Ignore
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}