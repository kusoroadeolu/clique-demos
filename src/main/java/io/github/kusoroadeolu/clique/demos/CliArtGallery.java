package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Tree;
import io.github.kusoroadeolu.clique.configuration.*;
import io.github.kusoroadeolu.clique.spi.RGBAnsiCode;
import io.github.kusoroadeolu.clique.style.ColorCode;
import io.github.kusoroadeolu.clique.style.Ink;
import io.github.kusoroadeolu.clique.style.StyleBuilder;

public final class CliArtGallery {


    private static final RGBAnsiCode G_SUNRISE_FROM = Clique.rgb(255, 0, 128);
    private static final RGBAnsiCode G_SUNRISE_TO   = Clique.rgb(255, 165, 0);

    private static final RGBAnsiCode G_OCEAN_FROM   = Clique.rgb(0,  60, 180);
    private static final RGBAnsiCode G_OCEAN_TO     = Clique.rgb(0, 230, 255);

    private static final RGBAnsiCode G_FOREST_FROM  = Clique.rgb(10,  80,  10);
    private static final RGBAnsiCode G_FOREST_TO    = Clique.rgb(80, 220,  60);

    private static final RGBAnsiCode G_DUSK_FROM    = Clique.rgb(120,  0, 200);
    private static final RGBAnsiCode G_DUSK_TO      = Clique.rgb(255, 80, 160);

    private static final RGBAnsiCode G_AURORA_FROM  = Clique.rgb(0, 200, 180);
    private static final RGBAnsiCode G_AURORA_TO    = Clique.rgb(160,  0, 255);

    private static final RGBAnsiCode G_FIRE_FROM    = Clique.rgb(255, 220,  0);
    private static final RGBAnsiCode G_FIRE_TO      = Clique.rgb(220,  20,  0);


    public static void main(String[] args) throws InterruptedException {
        displayGalleryEntrance();

        pause();
        exhibit1_Banner();

        pause();
        exhibit2_GradientShowcase();

        pause();
        exhibit3_StylizedQuotes();

        pause();
        exhibit4_ColorPalette();

        pause();
        exhibit5_ASCIIArt();

        pause();
        exhibit6_GeometricPatterns();

        pause();
        exhibit7_TreeAndList();

        pause();
        exhibit8_ProgressBars();

        pause();
        exhibit9_ThemeShowcase();

        pause();
        displayGalleryExit();
    }


    private static void displayGalleryEntrance()  {
        clearScreen();
        Clique.parser().print("\n\n");

        BoxConfiguration entranceCfg = BoxConfiguration.builder()
                .borderColor(ColorCode.BRIGHT_MAGENTA)
                .textAlign(TextAlign.CENTER)
                .padding(4)
                .build();

        Clique.box(BoxType.DOUBLE_LINE, entranceCfg)
                .content(
                        Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("✦  CLI  ART  GALLERY  ✦") + "\n\n" +
                                Clique.ink().italic().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("Where  Code  Meets  Creativity")
                )
                .render();

        System.out.println();
        Clique.parser().print("           [yellow]Press Enter to begin the tour...[/]");
    }

    private static void displayGalleryExit() {
        clearScreen();
        Clique.parser().print("\n\n");

        BoxConfiguration exitCfg = BoxConfiguration.builder()
                .borderColor(ColorCode.BRIGHT_CYAN)
                .textAlign(TextAlign.CENTER)
                .padding(4)
                .build();

        Clique.box(BoxType.DOUBLE_LINE, exitCfg)
                .content(
                        Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("✦  Thank You For Visiting!  ✦") + "\n\n" +
                                Clique.ink().italic().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("\"Make your terminal beautiful with Clique\"")
                )
                .render();

        System.out.println();
        // Gradient diamond row
        System.out.println("        " +
                Clique.ink().gradient(G_FIRE_FROM,   G_FIRE_TO  ).on("◆ ") +
                Clique.ink().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("◆ ") +
                Clique.ink().gradient(G_FOREST_FROM,  G_FOREST_TO ).on("◆ ") +
                Clique.ink().gradient(G_OCEAN_FROM,   G_OCEAN_TO  ).on("◆ ") +
                Clique.ink().gradient(G_DUSK_FROM,    G_DUSK_TO   ).on("◆ ") +
                Clique.ink().gradient(G_AURORA_FROM,  G_AURORA_TO ).on("◆")
        );
        System.out.println();
    }


    /**
     * Exhibit 1 — The CLIQUE banner, rendered as big block letters
     * where each letter is painted with its own Ink gradient.
     */
    private static void exhibit1_Banner() {
        clearScreen();
        exhibitHeader("Exhibit 1", "Rainbow Typography");

        // Each letter of CLIQUE gets its own gradient.
        // Block-letter rows are built by painting the same gradient per column.
        Ink C = Clique.ink().bold().gradient(G_FIRE_FROM,    G_FIRE_TO);
        Ink L = Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO);
        Ink I = Clique.ink().bold().gradient(G_FOREST_FROM,  G_FOREST_TO);
        Ink Q = Clique.ink().bold().gradient(G_OCEAN_FROM,   G_OCEAN_TO);
        Ink U = Clique.ink().bold().gradient(G_DUSK_FROM,    G_DUSK_TO);
        Ink E = Clique.ink().bold().gradient(G_AURORA_FROM,  G_AURORA_TO);

        // Each row of the 5-row block-letter banner
        // Format: 6 chars per letter, 2-char gap between
        String[][] rows = {
                // C             L             I         Q               U             E
                {"██████  ", "██      ", "██████  ", "██████  ", "██  ██  ", "██████"},
                {"██      ", "██      ", "  ██    ", "██  ██  ", "██  ██  ", "██    "},
                {"██      ", "██      ", "  ██    ", "██  ██  ", "██  ██  ", "████  "},
                {"██      ", "██      ", "  ██    ", "██████  ", "██  ██  ", "██    "},
                {"██████  ", "██████  ", "██████  ", "  ████  ", "██████  ", "██████"},
        };

        System.out.println();
        for (String[] row : rows) {
            System.out.print("     ");
            System.out.print(C.on(row[0]));
            System.out.print(L.on(row[1]));
            System.out.print(I.on(row[2]));
            System.out.print(Q.on(row[3]));
            System.out.print(U.on(row[4]));
            System.out.println(E.on(row[5]));
        }
        System.out.println();

        // Tagline — full gradient sweep across the whole string
        String tagline = "Make  Your  CLI  Beautiful";
        System.out.print("     ");
        System.out.println(
                Clique.ink().bold().gradient(G_SUNRISE_FROM, G_AURORA_TO).on(tagline)
        );
        System.out.println();

        // Spectrum bar — each block pair is its own gradient swatch
        System.out.print("     ");
        System.out.print(Clique.ink().gradient(G_FIRE_FROM,    G_FIRE_TO   ).on("████"));
        System.out.print(Clique.ink().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("████"));
        System.out.print(Clique.ink().gradient(G_FOREST_FROM,  G_FOREST_TO ).on("████"));
        System.out.print(Clique.ink().gradient(G_OCEAN_FROM,   G_OCEAN_TO  ).on("████"));
        System.out.print(Clique.ink().gradient(G_DUSK_FROM,    G_DUSK_TO   ).on("████"));
        System.out.print(Clique.ink().gradient(G_AURORA_FROM,  G_AURORA_TO ).on("████"));
        System.out.println(Clique.ink().dim().on("  Color Spectrum"));

        exhibitFooter("Ink gradient per letter + StyleBuilder chaining");
    }

    /**
     * Exhibit 2 — Pure gradient showcase: named gradient swatches,
     * a full-width sweep, and a gradient-bordered title card.
     */
    private static void exhibit2_GradientShowcase() {
        clearScreen();
        exhibitHeader("Exhibit 2", "Gradient Showcase");

        // Named swatches — thick gradient bars with label
        Object[][] swatches = {
                { G_SUNRISE_FROM, G_SUNRISE_TO, " Sunrise  " },
                { G_OCEAN_FROM,   G_OCEAN_TO,   " Ocean    " },
                { G_FOREST_FROM,  G_FOREST_TO,  " Forest   " },
                { G_DUSK_FROM,    G_DUSK_TO,    " Dusk     " },
                { G_AURORA_FROM,  G_AURORA_TO,  " Aurora   " },
                { G_FIRE_FROM,    G_FIRE_TO,    " Fire     " },
        };

        System.out.println();
        for (Object[] s : swatches) {
            RGBAnsiCode from = (RGBAnsiCode) s[0];
            RGBAnsiCode to   = (RGBAnsiCode) s[1];
            String label     = (String)      s[2];

            String bar = Clique.ink().bold().gradient(from, to).on("████████████████████████████████");
            String name = Clique.ink().italic().gradient(from, to).on(label);
            System.out.println("  " + bar + "  " + name);
        }

        System.out.println();

        // Full-width rainbow sweep
        String sweep = Clique.ink().bold().gradient(G_FIRE_FROM, G_AURORA_TO)
                .on("████████████████████████████████████████████████████████");
        System.out.println("  " + sweep);
        System.out.println("  " + Clique.ink().dim().on("Full spectrum sweep"));
        System.out.println();

        BoxConfiguration tagCfg = BoxConfiguration.builder()
                .borderColor(ColorCode.BRIGHT_MAGENTA)
                .textAlign(TextAlign.CENTER)
                .padding(3)
                .build();

        Clique.box(BoxType.DOUBLE_LINE, tagCfg)
                .content(
                        Clique.ink().bold().gradient(G_SUNRISE_FROM, G_AURORA_TO)
                                .on("✦  Gradients are just the start  ✦")
                )
                .render();

        exhibitFooter("Ink.gradient() across all 6 named palettes");
    }

    /**
     * Exhibit 3 — Stylized quotes with decorative hand-drawn borders
     * and gradient accents on the punctuation.
     */
    private static void exhibit3_StylizedQuotes() {
        clearScreen();
        exhibitHeader("Exhibit 3", "Inspirational Quotes");

        // Quote 1 — ocean-bordered box
        String openQ  = Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("\"");
        String closeQ = Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("\"");

        BoxConfiguration q1Cfg = BoxConfiguration.builder()
                .borderColor(ColorCode.CYAN)
                .textAlign(TextAlign.CENTER)
                .padding(3)
                .build();

        Clique.box(BoxType.CLASSIC, q1Cfg)
                .content(
                        openQ + Clique.ink().italic().on("Code is poetry, make it beautiful") + closeQ + "\n" +
                                Clique.ink().dim().on("— Anonymous")
                )
                .render();
        System.out.println();

        // Quote 2 — gradient punctuation, plain body
        System.out.println("  " +
                Clique.ink().bold().gradient(G_DUSK_FROM, G_DUSK_TO).on("❝") + " " +
                Clique.ink().bold().on("Simplicity") + " " +
                Clique.ink().on("is the ultimate") + " " +
                Clique.ink().bold().gradient(G_FOREST_FROM, G_FOREST_TO).on("sophistication") + " " +
                Clique.ink().bold().gradient(G_DUSK_FROM, G_DUSK_TO).on("❞")
        );
        System.out.println("                                  " + Clique.ink().dim().italic().on("— da Vinci"));
        System.out.println();

        // Quote 3 — freeform colored flow
        System.out.println("  " +
                Clique.ink().bold().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("╭─── ") +
                Clique.ink().bold().gradient(G_FIRE_FROM, G_FIRE_TO).on("\"") +
                Clique.ink().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("First") +
                Clique.ink().on(", solve the ") +
                Clique.ink().bold().gradient(G_FOREST_FROM, G_FOREST_TO).on("problem") +
                Clique.ink().on(".")
        );
        System.out.println("       " +
                Clique.ink().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("Then") +
                Clique.ink().on(", write the ") +
                Clique.ink().bold().gradient(G_DUSK_FROM, G_DUSK_TO).on("code") +
                Clique.ink().on(".") +
                Clique.ink().bold().gradient(G_FIRE_FROM, G_FIRE_TO).on("\"")
        );
        System.out.println("  " +
                Clique.ink().bold().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("╰─── ") +
                Clique.ink().dim().on("— John Johnson")
        );
        System.out.println();

        // Quote 4 — gradient-bordered box via Clique.box
        BoxConfiguration boxCfg = BoxConfiguration.builder()
                .borderColor(ColorCode.BRIGHT_YELLOW)
                .textAlign(TextAlign.CENTER)
                .build();

        Clique.box(BoxType.ROUNDED, boxCfg)
                .content(
                        Clique.ink().bold().italic().gradient(G_SUNRISE_FROM, G_AURORA_TO)
                                .on("The only way to do great work") + "\n" +
                                Clique.ink().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("is to ") +
                                Clique.ink().bold().gradient(G_FOREST_FROM, G_FOREST_TO).on("love") + " " +
                                Clique.ink().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("what you do") + "\n" +
                                Clique.ink().dim().on("— Steve Jobs")
                )
                .render();

        exhibitFooter("Ink gradients on punctuation + decorative borders");
    }

    /**
     * Exhibit 4 — Full color palette: standard, bright, backgrounds, styles.
     */
    private static void exhibit4_ColorPalette() {
        clearScreen();
        exhibitHeader("Exhibit 4", "Color Palette");

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

        Clique.parser().print("  [bold]Text Styles:[/]");
        Clique.parser().print("  [red, bold]Bold[/]  [green, dim]Dim[/]  [blue, italic]Italic[/]  [yellow, ul]Underline[/]  [magenta, rv]Reverse[/]  [cyan, strike]Strike[/]");
        System.out.println();

        // Color block art using gradients
        Clique.parser().print("  [bold]Color Blocks:[/]");
        System.out.print("  ");
        System.out.print(Clique.ink().gradient(G_FIRE_FROM,    G_FIRE_TO   ).on("████████"));
        System.out.print(Clique.ink().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("████████"));
        System.out.print(Clique.ink().gradient(G_FOREST_FROM,  G_FOREST_TO ).on("████████"));
        System.out.print(Clique.ink().gradient(G_OCEAN_FROM,   G_OCEAN_TO  ).on("████████"));
        System.out.print(Clique.ink().gradient(G_DUSK_FROM,    G_DUSK_TO   ).on("████████"));
        System.out.println(Clique.ink().gradient(G_AURORA_FROM,  G_AURORA_TO ).on("████████"));

        exhibitFooter("All 32 ANSI colors + Ink gradient blocks");
    }

    /**
     * Exhibit 5 — ASCII art shapes: heart, star, tree, diamond,
     * each painted with a thematic gradient.
     */
    private static void exhibit5_ASCIIArt() {
        clearScreen();
        exhibitHeader("Exhibit 5", "Colored ASCII Art");

        Ink heartInk  = Clique.ink().bold().gradient(G_FIRE_FROM,    G_FIRE_TO);
        Ink starInk   = Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO);
        Ink treeInk   = Clique.ink().bold().gradient(G_FOREST_FROM,  G_FOREST_TO);
        Ink diamInk   = Clique.ink().bold().gradient(G_OCEAN_FROM,   G_OCEAN_TO);

        // Rainbow Heart
        System.out.println("  " + heartInk.on("  ♥♥    ♥♥  ") + "     " +
                Clique.ink().bold().gradient(G_FIRE_FROM, G_FIRE_TO).on("Rainbow Heart"));
        System.out.println("  " + heartInk.on(" ♥♥♥♥  ♥♥♥♥ "));
        System.out.println("  " + heartInk.on("  ♥♥♥♥♥♥♥♥  "));
        System.out.println("  " + heartInk.on("   ♥♥♥♥♥♥   "));
        System.out.println("  " + heartInk.on("    ♥♥♥♥    "));
        System.out.println("  " + heartInk.on("     ♥♥     "));
        System.out.println();

        // Gradient Star
        System.out.println("        " + starInk.on("★") + "              " +
                Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("Shining Star"));
        System.out.println("       " + starInk.on("★★★"));
        System.out.println("      " + starInk.on("★★★★★"));
        System.out.println("       " + starInk.on("★★★"));
        System.out.println("        " + starInk.on("★"));
        System.out.println();

        // Forest Tree
        System.out.println("         " + treeInk.on("▲") + "             " +
                Clique.ink().bold().gradient(G_FOREST_FROM, G_FOREST_TO).on("Pine Tree"));
        System.out.println("        " + treeInk.on("▲▲▲"));
        System.out.println("       " + treeInk.on("▲▲▲▲▲"));
        System.out.println("      " + treeInk.on("▲▲▲▲▲▲▲"));
        System.out.println("         " + Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on("█"));
        System.out.println();

        // Ocean Diamond
        System.out.println("         " + diamInk.on("◆") + "             " +
                Clique.ink().bold().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("Diamond"));
        System.out.println("        " + diamInk.on("◆◆◆"));
        System.out.println("       " + diamInk.on("◆◆◆◆◆"));
        System.out.println("        " + diamInk.on("◆◆◆"));
        System.out.println("         " + diamInk.on("◆"));

        exhibitFooter("Unicode symbols + Ink gradient fills");
    }

    /**
     * Exhibit 6 — Geometric patterns: checkerboard, gradient wave, gradient box,
     * and a rainbow decorative border.
     */
    private static void exhibit6_GeometricPatterns() {
        clearScreen();
        exhibitHeader("Exhibit 6", "Geometric Patterns");

        // Checkerboard
        Clique.parser().print("  [bold]Checkerboard:[/]");
        for (int i = 0; i < 4; i++) {
            System.out.print("  ");
            StyleBuilder row = Clique.styleBuilder();
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 0) {
                    row.append("██", ColorCode.WHITE);
                } else {
                    row.append("██", ColorCode.BRIGHT_BLACK);
                }
            }
            row.print();
        }
        System.out.println();

        // Gradient wave — each row shifted
        Clique.parser().print("  [bold]Gradient Wave:[/]");
        RGBAnsiCode[] waveFroms = { G_FIRE_FROM, G_SUNRISE_FROM, G_FOREST_FROM, G_OCEAN_FROM };
        RGBAnsiCode[] waveTos   = { G_FIRE_TO,   G_SUNRISE_TO,   G_FOREST_TO,   G_OCEAN_TO   };
        for (int i = 0; i < 4; i++) {
            System.out.println("  " +
                    Clique.ink().gradient(waveFroms[i], waveTos[i]).on("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓")
            );
        }
        System.out.println();

        // Gradient box mosaic
        Clique.parser().print("  [bold]Gradient Mosaic:[/]");
        RGBAnsiCode[][] mosaic = {
                { G_FIRE_FROM,    G_FIRE_TO,    G_SUNRISE_FROM, G_SUNRISE_TO },
                { G_FOREST_FROM,  G_FOREST_TO,  G_OCEAN_FROM,   G_OCEAN_TO   },
                { G_DUSK_FROM,    G_DUSK_TO,    G_AURORA_FROM,  G_AURORA_TO  },
        };
        for (RGBAnsiCode[] mosaicRow : mosaic) {
            System.out.print("  ");
            for (int c = 0; c < mosaicRow.length; c += 2) {
                System.out.print(Clique.ink().gradient(mosaicRow[c], mosaicRow[c + 1]).on("██████████"));
            }
            System.out.println();
        }
        System.out.println();

        // Decorative border
        Clique.parser().print("  [bold]Decorative Border:[/]");
        BoxConfiguration borderCfg = BoxConfiguration.builder()
                .borderColor(ColorCode.BRIGHT_CYAN)
                .padding(3)
                .build();

        Clique.box(BoxType.DOUBLE_LINE, borderCfg)
                .content(
                        Clique.ink().bold().gradient(G_SUNRISE_FROM, G_DUSK_TO).on("✦   Clique Makes CLI Art Easy!   ✦")
                )
                .render();

        exhibitFooter("Block chars + Ink gradient waves and mosaics");
    }

    /**
     * Exhibit 7 — Tree and ItemList as expressive art, not utility output.
     */
    private static void exhibit7_TreeAndList() {
        clearScreen();
        exhibitHeader("Exhibit 7", "Tree & List");

        Clique.parser().print("  [bold]Project Anatomy:[/]\n");

        TreeConfiguration treeCfg = TreeConfiguration.builder()
                .connectorColor("*cyan, bold")
                .build();

        Tree tree = Clique.tree(
                Clique.ink().bold().gradient(G_DUSK_FROM, G_DUSK_TO).on("✦ clique-lib/"),
                treeCfg
        );

        Tree src  = tree.add(Clique.ink().bold().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("src/"));
        Tree core = src.add(Clique.ink().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("core/"));
        core.add("[*green]Parser.java         [dim]✓ 312 lines");
        core.add("[*green]StyleResolver.java  [dim]✓ 198 lines");
        core.add("[yellow]Renderer.java       [dim]⚠ needs review");

        Tree ink = src.add(Clique.ink().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("ink/"));
        ink.add("[*green]Ink.java            [dim]✓ 201 lines");
        ink.add("[*green]RGBAnsiCode.java    [dim]✓ 88 lines");

        Tree tests = tree.add(Clique.ink().bold().gradient(G_FOREST_FROM, G_FOREST_TO).on("tests/"));
        tests.add("[*green, bold]ParserTest.java     [dim]✓ 14/14 pass");
        tests.add("[*red, bold]RendererTest.java   [dim]✗  9/14 pass");
        tests.add("[dim, strike]InkTest.java        skipped");

        tree.add("[white]README.md");
        tree.add("[dim].gitignore");

        tree.render();
        System.out.println();

        // ItemList — Sprint board as art
        Clique.parser().print("  [bold]Sprint Board:[/]\n");

        ItemListConfiguration listCfg = ItemListConfiguration.builder()
                .indentSize(3)
                .symbolSpacing(2)
                .build();

        Clique.list(listCfg)
                .item(
                        Clique.ink().bold().gradient(G_DUSK_FROM, G_DUSK_TO).on("→"),
                        Clique.ink().bold().gradient(G_DUSK_FROM, G_AURORA_TO).on("Sprint 12"),
                        Clique.list()
                                .item("[*green]✓[/]", "[dim, strike]Auth service[/]")
                                .item("[*green]✓[/]", "[dim, strike]User profile page[/]")
                                .item("[yellow]~[/]", "Notification system — [yellow]in review[/]",
                                        Clique.list()
                                                .item("[dim]![/]", "Waiting on design sign-off")
                                )
                                .item("[*red]✗[/]", "[red]Payment integration[/]",
                                        Clique.list()
                                                .item("[dim]![/]", "Stripe keys not in env")
                                                .item("[dim]![/]", "Webhook endpoint missing")
                                )
                )
                .render();

        exhibitFooter("Tree + ItemList — hierarchy as expressive art");
    }

    /**
     * Exhibit 8 — All 5 progress bar presets animated live,
     * followed by a gradient dynamic-styled bar.
     */
    private static void exhibit8_ProgressBars() throws InterruptedException {
        clearScreen();
        exhibitHeader("Exhibit 8", "Progress Bars");

        String[] labels  = {"Blocks ", "Lines  ", "Bold   ", "Classic", "Dots   "};
        ProgressBarPreset[] presets = {
                ProgressBarPreset.BLOCKS,
                ProgressBarPreset.LINES,
                ProgressBarPreset.BOLD,
                ProgressBarPreset.CLASSIC,
                ProgressBarPreset.DOTS,
        };

        System.out.println();
        for (int p = 0; p < presets.length; p++) {
            System.out.print("  [" + labels[p] + "] ");
            var bar = Clique.progressBar(40, presets[p]);
            for (int i = 0; i < 40; i++) {
                bar.tick();
                Thread.sleep(15);
            }
            System.out.println();
        }

        System.out.println();
        Clique.parser().print("  [bold]Dynamic Styling — heat map:[/]");
        System.out.println();
        System.out.print("  ");

        ProgressBarConfiguration dynamicConfig = ProgressBarConfiguration.builder()
                .styleRange(0,  34, "[red]:bar[/]     [red]:percent%[/]  [dim]Starting...[/]")
                .styleRange(34, 67, "[yellow]:bar[/]  [yellow]:percent%[/]  [dim]In Progress...[/]")
                .styleRange(67, 100, "[*green]:bar[/] [*green]:percent%[/]  [dim]Almost Done![/]")
                .build();

        EasingConfiguration easing = EasingConfiguration.builder()
                .function(EasingFunction.EASE_OUT_CUBIC)
                .duration(900)
                .frames(40)
                .threshold(5)
                .build();

        ProgressBarConfiguration easedConfig = ProgressBarConfiguration.builder()
                .styleRange(0,  34, "[red]:bar[/]     [red]:percent%[/]  [dim]Starting...[/]")
                .styleRange(34, 67, "[yellow]:bar[/]  [yellow]:percent%[/]  [dim]In Progress...[/]")
                .styleRange(67, 100, "[*green]:bar[/] [*green]:percent%[/]  [dim]Almost Done![/]")
                .easing(easing)
                .build();

        var dynamicBar = Clique.progressBar(60, dynamicConfig);
        dynamicBar.tickAnimated(60);
        System.out.println();
        System.out.println();

        Clique.parser().print("  [bold]Eased jump — EASE_OUT_CUBIC:[/]");
        System.out.println();
        System.out.print("  ");
        var easedBar = Clique.progressBar(100, easedConfig);
        easedBar.tickAnimated(100);
        System.out.println();

        exhibitFooter("All 5 presets + dynamic ranges + EASE_OUT_CUBIC easing");
    }

    /**
     * Exhibit 9 — All 5 built-in themes shown with gradient-labeled headers.
     */
    private static void exhibit9_ThemeShowcase() {
        clearScreen();
        exhibitHeader("Exhibit 9", "Theme Gallery");

        Clique.registerAvailableThemes();

        // Helper: gradient theme label
        // Each theme gets a matching gradient header

        System.out.println(
                "  " + Clique.ink().bold().gradient(G_DUSK_FROM, G_AURORA_TO).on("Catppuccin Mocha") +
                        "  " + Clique.ink().dim().on("— Soothing pastel dark")
        );
        Clique.parser().print("  [ctp_mauve]●[/] [ctp_pink]●[/] [ctp_red]●[/] [ctp_peach]●[/] [ctp_yellow]●[/] [ctp_green]●[/] [ctp_blue]●[/] [ctp_lavender]●[/]");
        Clique.parser().print("  [bg_ctp_surface0, ctp_text] UI Box [/]  [ctp_green]✓ Success[/]  [ctp_red]✗ Error[/]  [ctp_yellow]⚠ Warn[/]");
        System.out.println();

        System.out.println(
                "  " + Clique.ink().bold().gradient(G_DUSK_FROM, G_DUSK_TO).on("Dracula") +
                        "  " + Clique.ink().dim().on("— Iconic purple-accented dark")
        );
        Clique.parser().print("  [drac_red]●[/] [drac_green]●[/] [drac_yellow]●[/] [drac_blue]●[/] [drac_magenta]●[/] [drac_cyan]●[/]");
        Clique.parser().print("  [drac_magenta]♦[/] [*drac_white, bold]Dracula[/]  [drac_green]✓[/]  [drac_red]✗[/]  [drac_yellow]⚠[/]");
        System.out.println();

        System.out.println(
                "  " + Clique.ink().bold().gradient(G_FIRE_FROM, G_SUNRISE_TO).on("Gruvbox Dark") +
                        "  " + Clique.ink().dim().on("— Retro warm earthy tones")
        );
        Clique.parser().print("  [gb_red]●[/] [gb_orange]●[/] [gb_yellow]●[/] [gb_green]●[/] [gb_aqua]●[/] [gb_blue]●[/] [gb_purple]●[/]");
        Clique.parser().print("  [gb_orange, bold]⚡[/] [bg_gb_bg1, gb_aqua] Info [/]  [gb_fg]Retro vibes[/]");
        System.out.println();

        System.out.println(
                "  " + Clique.ink().bold().gradient(G_OCEAN_FROM, G_OCEAN_TO).on("Nord") +
                        "  " + Clique.ink().dim().on("— Cool arctic-inspired")
        );
        Clique.parser().print("  [nord_frost0]●[/] [nord_frost1]●[/] [nord_frost2]●[/] [nord_frost3]●[/] [nord_red]●[/] [nord_yellow]●[/] [nord_green]●[/]");
        Clique.parser().print("  [nord_frost2]❄[/] [nord_snow, bold]Nordic[/]  [nord_green]✓[/]  [nord_red]✗[/]  [nord_yellow]⚠[/]");
        System.out.println();

        System.out.println(
                "  " + Clique.ink().bold().gradient(G_AURORA_FROM, G_DUSK_TO).on("Tokyo Night") +
                        "  " + Clique.ink().dim().on("— Modern purple-blue dark")
        );
        Clique.parser().print("  [tokyo_red]●[/] [tokyo_green]●[/] [tokyo_yellow]●[/] [tokyo_blue]●[/] [tokyo_magenta]●[/] [tokyo_cyan]●[/]");
        Clique.parser().print("  [tokyo_magenta]◆[/] [*tokyo_white, bold]Tokyo[/]  [tokyo_cyan]›[/] [tokyo_fg]Modern & clean[/]");
        System.out.println();

        System.out.println(
                "  " + Clique.ink().bold().gradient(G_FIRE_FROM, G_AURORA_TO).on("Mix & Match") +
                        "  " + Clique.ink().dim().on("— Combine freely")
        );
        Clique.parser().print("  [ctp_mauve]Catppuccin[/] + [tokyo_cyan]Tokyo Night[/] + [nord_frost2]Nord[/] + [drac_magenta]Dracula[/] + [gb_orange]Gruvbox[/]");

        exhibitFooter("5 built-in themes + Ink gradient labels + free mixing");
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    private static void exhibitHeader(String number, String title) {
        System.out.println();
        System.out.print("  ");
        System.out.print(Clique.ink().bold().gradient(G_SUNRISE_FROM, G_SUNRISE_TO).on(number));
        System.out.print(Clique.ink().dim().on(" │ "));
        System.out.println(Clique.ink().bold().gradient(G_AURORA_FROM, G_DUSK_TO).on(title));
        System.out.println("  " + Clique.ink().dim().on("─".repeat(50)));
        System.out.println();
    }

    private static void exhibitFooter(String technique) {
        System.out.println();
        System.out.println("  " + Clique.ink().dim().on("Technique: " + technique));
        System.out.println();
        Clique.parser().print("  [yellow]Press Enter to continue...[/]");
    }

    private static void pause() {
        try {
            do System.in.read();
            while (System.in.available() > 0);
        } catch (Exception ignored) {}
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}