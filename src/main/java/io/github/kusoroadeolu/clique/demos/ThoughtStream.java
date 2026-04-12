package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Tree;
import io.github.kusoroadeolu.clique.configuration.BoxType;
import io.github.kusoroadeolu.clique.configuration.FrameAlign;
import io.github.kusoroadeolu.clique.configuration.TreeConfiguration;
import io.github.kusoroadeolu.clique.style.ColorCode;


import java.util.*;

/**
 * AI Thought Stream — a real-time "reasoning" visualizer using Clique.
 *
 * Simulates an LLM thinking through a problem.
 * Each branch is color-coded by confidence:
 *   [green]  = certain / high confidence
 *   [yellow] = uncertain / exploring
 *   [red]    = contradiction / dead end
 *
 * Uses Frame + Tree for layout, Box for the status bar.
 */
public class ThoughtStream {

    // ANSI escape to clear terminal and move cursor to top-left
    private static final String CLEAR = "\033[2J\033[H";

    // Each "thought" is a node to add: path of parent indices, label, delay ms
    record Thought(int[] path, String label, long delayMs) {}

    public static void main(String[] args) throws InterruptedException {


        String question = "Is this decision reversible?";

        List<ThoughtStep> script = List.of(
            // root children
            step(new int[]{},    "[*green, bold]Analyzing the core question...",                    600),
            step(new int[]{0},   "[green]Identify: what does 'reversible' mean here?",             500),
            step(new int[]{0,0}, "[green]✓ Reversible = can be undone within 24h at low cost",     400),
            step(new int[]{},    "[yellow]Exploring assumptions...",                                700),
            step(new int[]{1},   "[yellow]⚠ Assumes cost model is static",                         500),
            step(new int[]{1},   "[yellow]⚠ Assumes stakeholder approval not required",            500),
            step(new int[]{1,0}, "[red]✗ Contradicts: approval IS required per policy §3.2",       600),
            step(new int[]{},    "[yellow]Checking alternatives...",                                700),
            step(new int[]{2},   "[green]Alt A: defer decision by 48h — fully reversible",         400),
            step(new int[]{2},   "[yellow]Alt B: proceed with rollback plan — partially reversible",400),
            step(new int[]{2},   "[red]✗ Alt C: immediate full rollout — irreversible",            400),
            step(new int[]{2,0}, "[green]✓ Alt A has no downstream dependencies",                  500),
            step(new int[]{2,1}, "[yellow]⚠ Alt B rollback triggers 6h downtime",                  500),
            step(new int[]{},    "[*green, bold]Forming conclusion...",                             800),
            step(new int[]{3},   "[green]✓ Recommend Alt A — reversible, low cost, low risk",      500),
            step(new int[]{3},   "[green]✓ Flag policy §3.2 for stakeholder review",               400),
            step(new int[]{3},   "[dim][ confidence: 87% ]",                                       300)
        );

        // ── Build initial empty tree ─────────────────────────────────────────
        Tree root = Clique.tree("[*blue, bold]⟨ reasoning ⟩", "*blue, bold");


        // ── Animate ──────────────────────────────────────────────────────────
        int totalSteps = script.size();
        Map<String, Tree> nodeByPath = new HashMap<>();
        Map<String, Integer> childCount = new HashMap<>(); // tracks how many children each parent has

        for (int i = 0; i < totalSteps; i++) {
            ThoughtStep step = script.get(i);
            Thread.sleep(step.delayMs);

            Tree parent = resolveParent(root, nodeByPath, step.path);

            Tree newNode = parent.add(step.label);

            // Compute this node's full path
            String parentKey = Arrays.toString(step.path);
            int siblingIndex = childCount.getOrDefault(parentKey, 0);
            childCount.put(parentKey, siblingIndex + 1);

            int[] fullPath = Arrays.copyOf(step.path, step.path.length + 1);
            fullPath[step.path.length] = siblingIndex;
            nodeByPath.put(Arrays.toString(fullPath), newNode);

            render(root, question, i + 1, totalSteps);
        }

        // ── Final status box ─────────────────────────────────────────────────
        Thread.sleep(500);
        System.out.println();
        Clique.box(BoxType.DOUBLE_LINE, ColorCode.GREEN)
              .content("[green, bold]✓ Reasoning complete.[/]\n" +
                       "[dim]Recommend: defer 48h (Alt A) | confidence: 87%[/]")
              .render();
    }

    // ── Render the current state ─────────────────────────────────────────────
    private static void render(Tree root, String question, int step, int total) {
        System.out.print(CLEAR);

        // Status line box at top
        Clique.box(BoxType.ROUNDED, ColorCode.BLUE)
              .content("[*blue, bold]AI Thought Stream[/]  [dim]|[/]  " +
                       "[white]" + question + "[/]  " +
                       "[dim]step " + step + "/" + total + "[/]")
              .render();

        System.out.println();

        // Frame wrapping the live tree
        Clique.frame(BoxType.CLASSIC, ColorCode.BLUE)
              .title("[*blue, bold] reasoning graph [/]", FrameAlign.CENTER)
              .nest(root, FrameAlign.LEFT)
              .render();

        System.out.println();
        System.out.print("[dim]▸ thinking...[/]");
        // Flush parsed output (the above dim tag won't parse standalone — just raw text is fine)
        System.out.print("\033[2K\r");
        Clique.parser().print("[dim]▸ thinking...[/]");
    }

    // ── Resolve parent from path ─────────────────────────────────────────────
    private static Tree resolveParent(Tree root, Map<String, Tree> nodeByPath, int[] path) {
        if (path.length == 0) return root;
        return nodeByPath.get(Arrays.toString(path));
    }

    // ── ThoughtStep record ───────────────────────────────────────────────────
    record ThoughtStep(int[] path, String label, long delayMs) {}

    static ThoughtStep step(int[] path, String label, long delayMs) {
        return new ThoughtStep(path, label, delayMs);
    }
}