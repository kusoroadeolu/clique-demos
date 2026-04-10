package io.github.kusoroadeolu.cliquedemos;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Tree;
import io.github.kusoroadeolu.clique.configuration.BoxType;
import io.github.kusoroadeolu.clique.configuration.FrameAlign;
import io.github.kusoroadeolu.clique.configuration.TreeConfiguration;
import io.github.kusoroadeolu.clique.style.ColorCode;

import java.lang.reflect.Type;
import java.util.*;

public class GeminiThoughtStream {

    static final String GEMINI_API_KEY = "API_KEY";
    private static final String MODEL = "gemini-2.5-flash";

    record ThoughtStep(int[] path, String label, long delayMs) {}

    private static List<ThoughtStep> fetchThoughtSteps(String question) {
        Client client = Client.builder().apiKey(GEMINI_API_KEY).build();

        String prompt = """
            You are a reasoning engine. Think through the following question step by step,
            and return your reasoning as a JSON array of thought steps.

            Question: "%s"

            Each thought step must be a well and fully formed JSON object with exactly these fields:
            - "path": an int array representing the parent node's position in the tree.
                      [] means a top-level node (child of root).
                      [0] means child of the 1st top-level node.
                      [1, 0] means child of the 1st child of the 2nd top-level node.
                      Indices are zero-based and refer to insertion order at that level.
            - "label": a string with a color tag prefix based on confidence:
                       "[green]" for certain/confirmed thoughts
                       "[yellow]" for uncertain/exploratory thoughts
                       "[red]" for contradictions or dead ends
                       You may also use "[*green, bold]" or "[*yellow, bold]" for section headers.
                       Keep the text after the color tag concise — max 45 - 60 characters.
                       You may also use "[*green, bold]" or "[*yellow, bold]" for section headers.
            - "delayMs": a long between 300 and 800 representing animation delay in milliseconds.

            Rules:
            - Start with 3-5 top-level nodes (path = []) as major reasoning branches.
            - Each top-level node should have 2-4 children.
            - Some children may have their own children (max depth 3).
            - End with a top-level "[*green, bold] Conclusion..." node with 2-3 green children summarizing the answer.
            - Total steps: between 15 and 25.
            - Return ONLY the raw JSON array. No markdown, no explanation, no backticks.
            """.formatted(question);

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .build();

        GenerateContentResponse response = client.models.generateContent(MODEL, prompt, config);
        String json = response.text();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        List<Map<String, Object>> raw = gson.fromJson(json, listType);
        if (raw == null) raw = List.of();

        List<ThoughtStep> steps = new ArrayList<>();
        for (Map<String, Object> entry : raw) {
            @SuppressWarnings("unchecked")
            List<Double> rawPath = (List<Double>) entry.get("path");
            int[] path = rawPath.stream().mapToInt(Double::intValue).toArray();

            String label = (String) entry.get("label");
            long delayMs = ((Double) entry.get("delayMs")).longValue();

            steps.add(new ThoughtStep(path, label, delayMs));
        }

        return steps;
    }

    // ── Main ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) throws InterruptedException {
        Clique.parser().print("[*blue, bold]Consulting Gemini...");
        StringBuilder question = new StringBuilder();
        for (String s: args){
            question.append(s).append(" ");
        }

        List<ThoughtStep> script = fetchThoughtSteps(question.toString());

        TreeConfiguration treeConfig = TreeConfiguration.builder()
                .connectorColor("red")
                .build();

        Tree root = Clique.tree("[*magenta, bold]⟨ reasoning ⟩", treeConfig);

        int totalSteps = script.size();
        Map<String, Tree> nodeByPath = new HashMap<>();
        Map<String, Integer> childCount = new HashMap<>();

        for (int i = 0; i < totalSteps; i++) {
            ThoughtStep step = script.get(i);
            Thread.sleep(step.delayMs);

            Tree parent = resolveParent(root, nodeByPath, step.path);
            Tree newNode = parent.add(step.label);

            String parentKey = Arrays.toString(step.path);
            int siblingIndex = childCount.getOrDefault(parentKey, 0);
            childCount.put(parentKey, siblingIndex + 1);

            int[] fullPath = Arrays.copyOf(step.path, step.path.length + 1);
            fullPath[step.path.length] = siblingIndex;
            nodeByPath.put(Arrays.toString(fullPath), newNode);

            render(root, question.toString(), i + 1, totalSteps);
        }

        Thread.sleep(500);
        System.out.println();
        Clique.box(BoxType.DOUBLE_LINE, ColorCode.GREEN)
              .content("[green, bold]✓ Reasoning complete.[/]\n" +
                       "[dim]Powered by Gemini " + MODEL + "[/]")
              .render();
    }

    // ── Render ───────────────────────────────────────────────────────────────
    private static int lastSize = 0;

    private static void render(Tree root, String question, int step, int total) {
        // 1. Capture the header as a String instead of rendering it immediately
        String header = Clique.box(ColorCode.BLUE)
                .content("[*blue, bold]AI Thought Stream[/]  [dim]|[/]  " +
                        "[white]" + question + "[/]  " +
                        "[dim]step " + step + "/" + total + "[/]")
                .get();

        // 2. Capture the tree
        String tree = Clique.frame(BoxType.CLASSIC, ColorCode.BLUE)
                .title("[*blue, bold] reasoning graph [/]", FrameAlign.CENTER)
                .nest(root, FrameAlign.LEFT)
                .get();

        // 3. Combine everything into a single frame (notice the \n\n for the blank line)
        String output = header + "\n\n" + tree + "\n▸ thinking...";
        var lines = output.lines().toList();

        // 4. Move cursor up and clear downward FIRST, using the old total size
        if (lastSize > 0) {
            System.out.print("\033[" + lastSize + "A");
            System.out.print("\033[J");
        }

        // 5. Update lastSize to the NEW total line count
        lastSize = lines.size();

        // 6. Draw the complete frame
        for (var line : lines) {
            System.out.println("\r" + line);
        }
    }

    // ── Resolve parent ───────────────────────────────────────────────────────
    private static Tree resolveParent(Tree root, Map<String, Tree> nodeByPath, int[] path) {
        if (path.length == 0) return root;
        Tree parent = nodeByPath.get(Arrays.toString(path));
        return parent != null ? parent : root; // fallback to root if path is invalid
    }
}