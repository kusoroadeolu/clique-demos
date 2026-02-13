package com.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.ansi.ColorCode;
import io.github.kusoroadeolu.clique.ansi.StyleCode;
import io.github.kusoroadeolu.clique.config.BorderStyle;
import io.github.kusoroadeolu.clique.config.CellAlign;
import io.github.kusoroadeolu.clique.config.TableConfiguration;
import io.github.kusoroadeolu.clique.style.StyleBuilder;
import io.github.kusoroadeolu.clique.tables.Table;
import io.github.kusoroadeolu.clique.tables.TableType;

public final class CliArtGallery {

    public static void main(String[] args) {
        displayGalleryEntrance();

        pause();
        exhibit1_RainbowText();

        pause();
        exhibit2_GradientBanner();

        pause();
        exhibit3_StylizedQuotes();

        pause();
        exhibit4_ColorPalette();

        pause();
        exhibit5_ASCIIArt();

        pause();
        exhibit6_PatternDesign();

        pause();
        exhibit7_ThemeShowcase();

        pause();
        displayGalleryExit();
    }

    private static void displayGalleryEntrance() {
        clearScreen();
        Clique.parser().print("\n\n");

        // Use table for perfect alignment
        BorderStyle style = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.BRIGHT_CYAN)
                .verticalBorderStyles(ColorCode.BRIGHT_CYAN)
                .edgeBorderStyles(ColorCode.BRIGHT_CYAN)
                .build();

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .borderStyle(style)
                .parser(Clique.parser())
                .padding(3)
                .alignment(CellAlign.CENTER)
                .build();

        var entrance = Clique.table(TableType.BOX_DRAW, config);
        entrance.addHeaders("[*magenta, bold] CLI ART GALLERY [/]")
                .addRows("[dim]Where Code Meets Creativity[/]").
                render();

        Clique.parser().print("\n           [yellow]Press Enter to begin tour...[/]");
    }

    private static void exhibit1_RainbowText() {
        clearScreen();
        exhibitHeader("Exhibit 1", "Rainbow Typography");

        // Rainbow "CLIQUE" using parser - bigger!
        Clique.parser().print("\n");
        Clique.parser().print("     [*red, bold]██████[/]  [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██████[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██████[/]");
        Clique.parser().print("     [*red, bold]██[/]      [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██[/]  [*blue, bold]██[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██[/]");
        Clique.parser().print("     [*red, bold]██[/]      [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██[/]  [*blue, bold]██[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██████[/]");
        Clique.parser().print("     [*red, bold]██[/]      [*yellow, bold]██[/]      [*green, bold]██[/]  [*blue, bold]██[/]  [*blue, bold]██[/]  [*magenta, bold]██[/]  [*cyan, bold]██[/] [*white, bold]██[/]");
        Clique.parser().print("     [*red, bold]██████[/]  [*yellow, bold]██████[/]  [*green, bold]██[/]  [*blue, bold]██████[/]  [*magenta, bold]██████[/] [*white, bold]██████[/]");
        System.out.println("\n");

        // Rainbow message letter by letter
        String message = "Make Your CLI Beautiful";
        ColorCode[] rainbow = {
                ColorCode.RED, ColorCode.BRIGHT_RED, ColorCode.YELLOW, ColorCode.BRIGHT_YELLOW,
                ColorCode.GREEN, ColorCode.BRIGHT_GREEN, ColorCode.CYAN, ColorCode.BRIGHT_CYAN,
                ColorCode.BLUE, ColorCode.BRIGHT_BLUE, ColorCode.MAGENTA, ColorCode.BRIGHT_MAGENTA
        };

        System.out.print("     ");
        StyleBuilder sb = Clique.styleBuilder();
        for (int i = 0; i < message.length(); i++) {
            ColorCode color = rainbow[i % rainbow.length];
            sb.append(String.valueOf(message.charAt(i)), color, StyleCode.BOLD);
        }
        sb.print();

        // Color spectrum
        System.out.println();
        Clique.parser().print("     [red]█[/][*red]█[/][yellow]█[/][*yellow]█[/][green]█[/][*green]█[/][cyan]█[/][*cyan]█[/][blue]█[/][*blue]█[/][magenta]█[/][*magenta]█[/] [dim]Color Spectrum[/]");

        exhibitFooter("Markup parsing + StyleBuilder chaining");
    }

    private static void exhibit2_GradientBanner() {
        clearScreen();
        exhibitHeader("Exhibit 2", "Gradient Banners");

        // Red to Yellow gradient
        Clique.parser().print("  [*red]▓[/][*red]▓[/][red]▓[/][red]▒[/][*yellow]▒[/][*yellow]░[/][yellow]░[/] [white, bold]Sunrise Gradient[/]");
        System.out.println();

        // Blue to Cyan gradient banner
        Clique.parser().print("  [*blue]███[/][*blue]███[/][blue]███[/][*cyan]███[/][cyan]███[/] [white, bold]Ocean Wave[/]");
        System.out.println();

        // Green gradient
        Clique.parser().print("  [*green]▓▓▓[/][green]▒▒▒[/][dim, green]░░░[/] [white, bold]Forest Fade[/]");
        System.out.println();

        // Full spectrum banner
        System.out.println();
        for (int i = 0; i < 3; i++) {
            Clique.parser().print("  [red]█[/][*red]█[/][yellow]█[/][*yellow]█[/][green]█[/][*green]█[/][cyan]█[/][*cyan]█[/][blue]█[/][*blue]█[/][magenta]█[/][*magenta]█[/][white]█[/][*white]█[/]");
        }
        System.out.println();
        System.out.println();

        // Title card with gradient effect
        Clique.parser().print("  [*magenta, bold]╔[/][magenta, bold]═[/][blue, bold]═[/][cyan, bold]═[/][green, bold]═[/][yellow, bold]═[/][red, bold]═[/][*red, bold]═[/][*yellow, bold]═[/][*green, bold]═[/][*cyan, bold]═[/][*blue, bold]═[/][*magenta, bold]╗[/]");
        Clique.parser().print("  [*magenta, bold]║[/]  [*white, bold]GRADIENT[/] [*magenta, bold]║[/]");
        Clique.parser().print("  [*magenta, bold]╚[/][magenta, bold]═[/][blue, bold]═[/][cyan, bold]═[/][green, bold]═[/][yellow, bold]═[/][red, bold]═[/][*red, bold]═[/][*yellow, bold]═[/][*green, bold]═[/][*cyan, bold]═[/][*blue, bold]═[/][*magenta, bold]╝[/]");

        exhibitFooter("Block characters + color transitions");
    }

    private static void exhibit3_StylizedQuotes() {
        clearScreen();
        exhibitHeader("Exhibit 3", "Inspirational Quotes");

        // Quote 1 - with decorative borders
        Clique.parser().print("  [*cyan]┌─────────────────────────────────────────────┐[/]");
        Clique.parser().print("  [*cyan]│[/] [*yellow]\"[/][white, italic]Code is poetry, make it beautiful[/][*yellow]\"[/]         [*cyan]│[/]");
        Clique.parser().print("  [*cyan]│[/]                          [dim]— Anonymous[/]        [*cyan]│[/]");
        Clique.parser().print("  [*cyan]└─────────────────────────────────────────────┘[/]");
        System.out.println();

        // Quote 2 - minimalist
        Clique.parser().print("  [*magenta]❝[/] [*white, bold]Simplicity[/] [white]is the ultimate[/] [*green, bold]sophistication[/] [*magenta]❞[/]");
        Clique.parser().print("                                [dim, italic]- da Vinci[/]");
        System.out.println();

        // Quote 3 - colorful
        Clique.parser().print("  [*blue]╭[/][blue]─[/][*cyan]─[/][cyan]─[/] [*red, bold]\"[/][*yellow]First[/][yellow], solve the[/] [*green]problem[/][green].[/]");
        Clique.parser().print("      [*cyan]Then[/][cyan], write the[/] [*blue]code[/][blue].[/][*red, bold]\"[/]");
        Clique.parser().print("  [*blue]╰[/][blue]─[/][*cyan]─[/][cyan]─[/] [dim]— John Johnson[/]");
        System.out.println();

        // Quote 4 - styled box
        BorderStyle quoteStyle = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.BRIGHT_YELLOW)
                .verticalBorderStyles(ColorCode.BRIGHT_YELLOW)
                .edgeBorderStyles(ColorCode.BRIGHT_MAGENTA)
                .build();

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .borderStyle(quoteStyle)
                .parser(Clique.parser())
                .padding(2)
                .alignment(CellAlign.CENTER)
                .build();

        var quoteTable = Clique.table(TableType.ROUNDED_BOX_DRAW, config);
        quoteTable.addHeaders("[*white, italic, bold]The only way to do great work[/]")
        .addRows("[*cyan]is to[/] [*green, bold]love[/] [*cyan]what you do[/]")
        .addRows("[dim]— Steve Jobs[/]").render();

        exhibitFooter("Styled typography + decorative elements");
    }

    private static void exhibit4_ColorPalette() {
        clearScreen();
        exhibitHeader("Exhibit 4", "Color Palette Showcase");

        // Standard colors
        Clique.parser().print("  [bold]Standard Colors:[/]");
        Clique.parser().print("  [black]■ Black  [/] [red]■ Red    [/] [green]■ Green  [/] [yellow]■ Yellow [/]");
        Clique.parser().print("  [blue]■ Blue   [/] [magenta]■ Magenta[/] [cyan]■ Cyan   [/] [white]■ White  [/]");
        System.out.println();

        // Bright colors
        Clique.parser().print("  [bold]Bright Colors:[/]");
        Clique.parser().print("  [*black]■ Black  [/] [*red]■ Red    [/] [*green]■ Green  [/] [*yellow]■ Yellow [/]");
        Clique.parser().print("  [*blue]■ Blue   [/] [*magenta]■ Magenta[/] [*cyan]■ Cyan   [/] [*white]■ White  [/]");
        System.out.println();

        // Background colors
        Clique.parser().print("  [bold]Background Colors:[/]");
        Clique.parser().print("  [bg_red, white] RED [/] [bg_green, black] GREEN [/] [bg_blue, white] BLUE [/] [bg_yellow, black] YELLOW [/]");
        Clique.parser().print("  [bg_magenta, white] MAGENTA [/] [bg_cyan, black] CYAN [/] [*bg_red, white] BRIGHT [/]");
        System.out.println();

        // Style combinations
        Clique.parser().print("  [bold]Style Combinations:[/]");
        Clique.parser().print("  [red, bold]Bold[/] [green, dim]Dim[/] [blue, italic]Italic[/] [yellow, ul]Underline[/] [magenta, rv]Reverse[/]");
        System.out.println();

        // Color blocks as art
        Clique.parser().print("  [bold]Color Art:[/]");
        Clique.parser().print("  [bg_red]    [/][bg_*red]    [/][bg_yellow]    [/][bg_*yellow]    [/][bg_green]    [/][bg_*green]    [/]");
        Clique.parser().print("  [bg_cyan]    [/][bg_*cyan]   [/][bg_blue]    [/][bg_*blue]     [/] [bg_magenta]    [/][bg_*magenta]    [/]");

        exhibitFooter("All 32 ANSI colors + styles");
    }

    private static void exhibit5_ASCIIArt() {
        clearScreen();
        exhibitHeader("Exhibit 5", "Colored ASCII Art");

        // Rainbow heart
        Clique.parser().print("  [red]  ♥♥[/]    [red]♥♥[/]     [*red, bold]Rainbow Heart[/]");
        Clique.parser().print("  [yellow] ♥♥♥♥[/]  [yellow]♥♥♥♥[/]");
        Clique.parser().print("  [green]  ♥♥♥♥♥♥♥♥[/]");
        Clique.parser().print("  [cyan]   ♥♥♥♥♥♥[/]");
        Clique.parser().print("  [blue]    ♥♥♥♥[/]");
        Clique.parser().print("  [magenta]     ♥♥[/]");
        System.out.println();

        // Gradient star
        Clique.parser().print("        [*yellow]★[/]          [*yellow, bold]Shining Star[/]");
        Clique.parser().print("       [yellow]★[/][*yellow]★[/][yellow]★[/]");
        Clique.parser().print("      [yellow]★[/][*white]★[/][*yellow]★[/][white]★[/][yellow]★[/]");
        Clique.parser().print("       [yellow]★[/][*yellow]★[/][yellow]★[/]");
        Clique.parser().print("        [yellow]★[/]");
        System.out.println();

        // Colorful tree
        Clique.parser().print("        [*green]▲[/]          [*green, bold]Pine Tree[/]");
        Clique.parser().print("       [*green]▲▲▲[/]");
        Clique.parser().print("      [green]▲▲▲▲▲[/]");
        Clique.parser().print("     [green]▲▲▲▲▲▲▲[/]");
        Clique.parser().print("        [yellow, bold]█[/]");
        System.out.println();

        // Diamond
        Clique.parser().print("        [*cyan]◆[/]          [*cyan, bold]Diamond[/]");
        Clique.parser().print("       [*cyan]◆[/][cyan]◆[/][*cyan]◆[/]");
        Clique.parser().print("      [*cyan]◆[/][white]◆[/][*white]◆[/][white]◆[/][*cyan]◆[/]");
        Clique.parser().print("       [cyan]◆[/][*cyan]◆[/][cyan]◆[/]");
        Clique.parser().print("        [cyan]◆[/]");

        exhibitFooter("Unicode symbols + ANSI colors");
    }

    private static void exhibit6_PatternDesign() {
        clearScreen();
        exhibitHeader("Exhibit 6", "Geometric Patterns");

        // Checkerboard
        Clique.parser().print("  [bold]Checkerboard:[/]");
        for (int i = 0; i < 4; i++) {
            System.out.print("  ");
            StyleBuilder row = Clique.styleBuilder();
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    row.append("██", ColorCode.WHITE);
                } else {
                    row.append("██", ColorCode.BRIGHT_BLACK);
                }
            }
            row.print();
        }
        System.out.println();

        // Rainbow wave
        Clique.parser().print("  [bold]Rainbow Wave:[/]");
        ColorCode[] colors = {ColorCode.RED, ColorCode.YELLOW, ColorCode.GREEN, ColorCode.CYAN, ColorCode.BLUE, ColorCode.MAGENTA};
        StyleBuilder sb = Clique.styleBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                ColorCode color = colors[(j + i) % colors.length];
                sb.append("▓", color);
            }
            sb.append("\n");
        }
        sb.append("\n").print();

        // Gradient box
        Clique.parser().print("  [bold]Gradient Box:[/]");
        Clique.parser().print("  [*red]████[/][red]████[/][*yellow]████[/][yellow]████[/][*green]████[/]");
        Clique.parser().print("  [*red]████[/][red]████[/][*yellow]████[/][yellow]████[/][*green]████[/]");
        Clique.parser().print("  [red]████[/][*yellow]████[/][yellow]████[/][*green]████[/][green]████[/]");
        Clique.parser().print("  [red]████[/][*yellow]████[/][yellow]████[/][*green]████[/][green]████[/]");
        System.out.println();

        // Border art
        Clique.parser().print("  [bold]Decorative Border:[/]");
        Clique.parser().print("  [*magenta]╔[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]╗[/]");
        Clique.parser().print("  [*magenta]║[/] [*white, bold]Clique Makes CLI Art Easy![/] [*magenta] ║[/]");
        Clique.parser().print("  [*magenta]╚[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]═[/][*cyan]═[/][*blue]═[/][*green]═[/][*yellow]═[/][*red]═[/][*magenta]╝[/]");

        exhibitFooter("Block characters + color patterns");
    }

    private static void exhibit7_ThemeShowcase() {
        clearScreen();
        exhibitHeader("Exhibit 7", "Theme Gallery");

        // Register all themes
        Clique.registerAllThemes();

        // Catppuccin Mocha
        Clique.parser().print("  [bold, ul]Catppuccin Mocha[/] [dim]- Soothing pastel dark theme[/]");
        Clique.parser().print("  [ctp_mauve]●[/] [ctp_pink]●[/] [ctp_red]●[/] [ctp_peach]●[/] [ctp_yellow]●[/] [ctp_green]●[/] [ctp_blue]●[/] [ctp_lavender]●[/]");
        Clique.parser().print("  [bg_ctp_surface0, ctp_text] UI Box [/] [ctp_green]✓ Success[/] [ctp_red]✗ Error[/]");
        System.out.println();

        // Dracula
        Clique.parser().print("  [bold, ul]Dracula[/] [dim]- Iconic purple-accented dark theme[/]");
        Clique.parser().print("  [drac_red]●[/] [drac_green]●[/] [drac_yellow]●[/] [drac_blue]●[/] [drac_magenta]●[/] [drac_cyan]●[/]");
        Clique.parser().print("  [drac_magenta]♦[/] [*drac_white, bold]Dracula[/] [drac_green]✓[/] [drac_red]✗[/]");
        System.out.println();

        // Gruvbox Dark
        Clique.parser().print("  [bold, ul]Gruvbox Dark[/] [dim]- Retro warm dark palette[/]");
        Clique.parser().print("  [gruvbox_red]●[/] [gruvbox_orange]●[/] [gruvbox_yellow]●[/] [gruvbox_green]●[/] [gruvbox_aqua]●[/] [gruvbox_blue]●[/] [gruvbox_purple]●[/]");
        Clique.parser().print("  [gruvbox_orange, bold]⚡[/] [bg_gruvbox_bg1, gruvbox_aqua] Info [/] [gruvbox_fg]Retro vibes[/]");
        System.out.println();

        // Nord
        Clique.parser().print("  [bold, ul]Nord[/] [dim]- Cool arctic-inspired colors[/]");
        Clique.parser().print("  [nord_frost0]●[/] [nord_frost1]●[/] [nord_frost2]●[/] [nord_frost3]●[/] [nord_red]●[/] [nord_yellow]●[/] [nord_green]●[/]");
        Clique.parser().print("  [nord_frost2]❄[/] [nord_snow, bold]Nordic[/] [nord_green]✓[/] [nord_red]✗[/] [nord_yellow]⚠[/]");
        System.out.println();

        // Tokyo Night
        Clique.parser().print("  [bold, ul]Tokyo Night[/] [dim]- Modern purple-blue dark theme[/]");
        Clique.parser().print("  [tokyo_red]●[/] [tokyo_green]●[/] [tokyo_yellow]●[/] [tokyo_blue]●[/] [tokyo_magenta]●[/] [tokyo_cyan]●[/]");
        Clique.parser().print("  [tokyo_magenta]◆[/] [*tokyo_white, bold]Tokyo[/] [tokyo_cyan]›[/] [tokyo_fg]Modern & clean[/]");
        System.out.println();

        // Theme mixing example
        Clique.parser().print("  [bold, ul]Mix & Match[/] [dim]- Combine themes creatively[/]");
        Clique.parser().print("  [ctp_mauve]Catppuccin[/] + [tokyo_cyan]Tokyo Night[/] + [nord_frost2]Nord[/] + [drac_magenta]Dracula[/]");

        exhibitFooter("Pre-built themes + custom color palettes");
    }

    private static void displayGalleryExit() {
        clearScreen();
        Clique.parser().print("\n\n");

        // Use table for alignment
        BorderStyle style = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.BRIGHT_MAGENTA)
                .verticalBorderStyles(ColorCode.BRIGHT_MAGENTA)
                .edgeBorderStyles(ColorCode.BRIGHT_MAGENTA)
                .build();

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .borderStyle(style)
                .parser(Clique.parser())
                .padding(3)
                .alignment(CellAlign.CENTER)
                .build();

        var exit = Clique.table(TableType.BOX_DRAW, config);
        exit.addHeaders("[*cyan, bold]Thank You For Visiting! [/]").render();

        System.out.println();
        Clique.parser().print("             [red]◆[/] [yellow]◆[/] [green]◆[/] [cyan]◆[/] [blue]◆[/] [magenta]◆[/]");
        System.out.println();
        Clique.parser().print("       [*white, italic]\"Make your terminal beautiful with[/] [*magenta, bold]Clique[/][*white, italic]\"[/]");
        Clique.parser().print("\n\n");
    }

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