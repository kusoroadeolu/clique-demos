package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Tree;
import io.github.kusoroadeolu.clique.configuration.BoxType;
import io.github.kusoroadeolu.clique.configuration.FrameAlign;
import io.github.kusoroadeolu.clique.configuration.TableType;

public class BookReviewDemo {

    public static void main(String[] args) throws InterruptedException {

        // ── Rating ───────────────────────────────────────────────────────────
        int rating = 4; // out of 5
        String stars = "[yellow]" + "★".repeat(rating) + "[/]"
                + "[dim]" + "★".repeat(5 - rating) + "[/]";

        String ratingLine = stars + "  [yellow, bold]" + rating + "/5[/]  [dim]· 2,847 ratings[/]";

        // ── Metadata table ───────────────────────────────────────────────────
        var metaTable = Clique.table(TableType.ROUNDED_BOX_DRAW)
                .headers("[cyan, bold]Genre[/]", "[cyan, bold]Pages[/]", "[cyan, bold]Year[/]", "[cyan, bold]Language[/]")
                .row("[white]Gothic Fiction[/]", "[white]280[/]", "[white]1818[/]", "[white]English[/]");

        // ── Review blurb box ─────────────────────────────────────────────────
        var reviewBox = Clique.box(BoxType.CLASSIC)
                .dimensions(77, 7)
                .content(
                        """
                                "[dim, italic]A haunting meditation on creation, consequence, and what
                                [dim, italic]it means to be human. Shelley wrote this at 18 and somehow
                                [dim, italic]managed to invent an entire genre. The monster's chapters
                                [dim, italic]are genuinely heartbreaking — he deserved better.[/]"
                                
                                [dim]— reviewed by[/] [magenta]@marysghost[/]"""
                );

        // ── Similar reads tree ───────────────────────────────────────────────
        Tree similarReads = Clique.tree("[bold, cyan]If you liked this...");
        Tree gothic = similarReads.add("[white]Gothic classics");
        gothic.add("[green]Dracula[/]         [dim]Bram Stoker, 1897[/]");
        gothic.add("[green]The Strange Case[/] [dim]Stevenson, 1886[/]");
        Tree scifi = similarReads.add("[white]Philosophical sci-fi");
        scifi.add("[green]Blindsight[/]       [dim]Peter Watts, 2006[/]");
        scifi.add("[green]Flowers for Algernon[/] [dim]Keyes, 1966[/]");

        // ── Assemble the frame ───────────────────────────────────────────────
        Clique.frame(BoxType.DOUBLE_LINE)
                .title("[bold, *white]📖 BOOK REVIEW CARD[/]")
                .nest("\n[*white, bold]Frankenstein; or, The Modern Prometheus[/]", FrameAlign.LEFT)
                .nest("[dim]by[/] [italic, white]Mary Wollstonecraft Shelley[/]", FrameAlign.LEFT)
                .nest(" ")
                .nest(ratingLine, FrameAlign.LEFT)
                .nest(" ")
                .nest(metaTable, FrameAlign.LEFT)
                .nest(" ")
                .nest("[bold]Reader Review[/]", FrameAlign.LEFT)
                .nest(reviewBox, FrameAlign.LEFT)
                .nest(" ")
                .nest(similarReads, FrameAlign.LEFT)
                .nest(" ")
                .render();
    }
}