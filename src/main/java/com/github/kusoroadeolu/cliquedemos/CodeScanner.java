package com.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.config.CellAlign;
import io.github.kusoroadeolu.clique.config.TableConfiguration;
import io.github.kusoroadeolu.clique.tables.Table;
import io.github.kusoroadeolu.clique.tables.TableType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CodeScanner {

    private static class TodoItem {
        String file;
        int line;
        String text;
        String type;

        TodoItem(String file, int line, String text, String type) {
            this.file = file;
            this.line = line;
            this.text = text;
            this.type = type;
        }
    }

    private static class MethodInfo {
        String name;
        int lines;
        String file;

        MethodInfo(String name, int lines, String file) {
            this.name = name;
            this.lines = lines;
            this.file = file;
        }
    }

    private static class FileStats {
        String name;
        int lines;
        int methods;
        int todos;

        FileStats(String name, int lines, int methods, int todos) {
            this.name = name;
            this.lines = lines;
            this.methods = methods;
            this.todos = todos;
        }
    }

    public static void main(String[] args) {
        String projectPath = args.length > 0 ? args[0] : ".";
        Path root = Paths.get(projectPath);

        // Header
        Clique.parser().print("\n[*magenta, bold]╔════════════════════════════════════╗[/]");
        Clique.parser().print("[*magenta, bold]║    CODE QUALITY SCANNER 🔍         ║[/]");
        Clique.parser().print("[*magenta, bold]╚════════════════════════════════════╝[/]\n");

        Clique.parser().print("[dim]Scanning:[/] [yellow]" + root.toAbsolutePath() + "[/]\n");

        List<TodoItem> todos = new ArrayList<>();
        List<MethodInfo> methods = new ArrayList<>();
        List<FileStats> fileStats = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(root)) {
            for (Path path : (Iterable<Path>) paths::iterator) {
                if (!Files.isRegularFile(path)) continue;

                String fileName = path.getFileName().toString();
                if (!fileName.endsWith(".java")) continue;

                // Skip test files and build directories
                if (path.toString().contains("test") ||
                        path.toString().contains("target") ||
                        path.toString().contains(".git")) {
                    continue;
                }

                analyzeFile(path, todos, methods, fileStats);
            }
        } catch (IOException e) {
            Clique.parser().print("[red, bold]Error:[/] " + e.getMessage());
            return;
        }

        if (fileStats.isEmpty()) {
            Clique.parser().print("[yellow]No Java files found![/]");
            return;
        }

        // Display results
        displayFileComplexity(fileStats);
        displayLongMethods(methods);
        displayTodos(todos);
        displaySummary(fileStats, methods, todos);
    }

    private static void analyzeFile(Path path, List<TodoItem> todos,
                                    List<MethodInfo> methods, List<FileStats> fileStats) {
        try {
            List<String> lines = Files.readAllLines(path);
            String fileName = path.getFileName().toString();
            int lineCount = 0;
            int methodCount = 0;
            int todoCount = 0;

            boolean inMethod = false;
            int methodStart = 0;
            String currentMethod = "";
            int braceDepth = 0;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                lineCount++;

                // Skip empty lines and comments for method detection
                if (line.isEmpty() || line.startsWith("//") || line.startsWith("/*") || line.startsWith("*")) {
                    continue;
                }

                // Check for TODOs/FIXMEs
                if (line.contains("TODO") || line.contains("FIXME") || line.contains("HACK")) {
                    String type = line.contains("FIXME") ? "FIXME" : line.contains("HACK") ? "HACK" : "TODO";
                    String text = extractComment(line);
                    todos.add(new TodoItem(fileName, i + 1, text, type));
                    todoCount++;
                }

                // Method detection (simple heuristic)
                if (!inMethod && (line.contains("public ") || line.contains("private ") ||
                        line.contains("protected ")) && line.contains("(") &&
                        !line.contains("class ") && !line.contains("interface ")) {
                    inMethod = true;
                    methodStart = i;
                    currentMethod = extractMethodName(line);
                    braceDepth = 0;
                    methodCount++;
                }

                if (inMethod) {
                    braceDepth += countChar(line, '{');
                    braceDepth -= countChar(line, '}');

                    if (braceDepth == 0 && line.contains("}")) {
                        int methodLines = i - methodStart + 1;
                        methods.add(new MethodInfo(currentMethod, methodLines, fileName));
                        inMethod = false;
                    }
                }
            }

            fileStats.add(new FileStats(fileName, lineCount, methodCount, todoCount));

        } catch (IOException e) {
            // Skip files we can't read
        }
    }

    private static void displayFileComplexity(List<FileStats> fileStats) {
        Clique.parser().print("[*blue, bold]📁 File Complexity[/]\n");

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .parser(Clique.parser())
                .alignment(CellAlign.LEFT)
                .padding(2)
                .build();


        Table table = Clique.table(TableType.BOX_DRAW, config)
                .addHeaders("[cyan, bold]File[/]", "[cyan, bold]Lines[/]",
                "[cyan, bold]Methods[/]", "[cyan, bold]TODOs[/]", "[cyan, bold]Status[/]");

        fileStats.stream()
                .sorted((a, b) -> Integer.compare(b.lines, a.lines))
                .limit(10)
                .forEach(fs -> {
                    String truncated = truncate(fs.name, 35);
                    String status = getComplexityStatus(fs.lines, fs.methods);

                    table.addRows(
                            "[white]" + truncated + "[/]",
                            getLineColor(fs.lines) + fs.lines + "[/]",
                            "[yellow]" + fs.methods + "[/]",
                            fs.todos > 0 ? "[red]" + fs.todos + "[/]" : "[dim]0[/]",
                            status
                    );
                });

        table.render();
        System.out.println();
    }

    private static void displayLongMethods(List<MethodInfo> methods) {
        List<MethodInfo> longMethods = methods.stream()
                .filter(m -> m.lines > 30)
                .sorted((a, b) -> Integer.compare(b.lines, a.lines))
                .limit(5)
                .toList();

        if (longMethods.isEmpty()) {
            Clique.parser().print("[green]✓ No overly long methods found![/]\n");
            return;
        }

        Clique.parser().print("[*yellow, bold]⚠ Long Methods (>30 lines)[/]\n");

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .parser(Clique.parser())
                .alignment(CellAlign.LEFT)
                .padding(2)
                .build();

        var table = Clique.table(TableType.BOX_DRAW, config)
                .addHeaders("[cyan, bold]Method[/]", "[cyan, bold]Lines[/]", "[cyan, bold]File[/]");

        for (MethodInfo m : longMethods) {
            String severity = m.lines > 100 ? "red, bold" : m.lines > 50 ? "red" : "yellow";
            table.addRows(
                    "[white]" + truncate(m.name, 30) + "[/]",
                    "[" + severity + "]" + m.lines + "[/]",
                    "[dim]" + truncate(m.file, 30) + "[/]"
            );
        }

        table.render();
        System.out.println();
    }

    private static void displayTodos(List<TodoItem> todos) {
        if (todos.isEmpty()) {
            Clique.parser().print("[green]✓ No TODOs/FIXMEs found![/]\n");
            return;
        }

        Clique.parser().print("[*cyan, bold]📝 TODOs & FIXMEs[/]\n");

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .parser(Clique.parser())
                .alignment(CellAlign.LEFT)
                .padding(2)
                .build();

        var table = Clique.table(TableType.BOX_DRAW, config)
                .addHeaders("[cyan, bold]Type[/]", "[cyan, bold]File[/]",
                "[cyan, bold]Line[/]", "[cyan, bold]Comment[/]");

        todos.stream()
                .limit(10)
                .forEach(todo -> {
                    String typeColor = todo.type.equals("FIXME") ? "red" :
                            todo.type.equals("HACK") ? "magenta" : "yellow";
                    table.addRows(
                            "[" + typeColor + ", bold]" + todo.type + "[/]",
                            "[dim]" + truncate(todo.file, 25) + "[/]",
                            "[white]" + todo.line + "[/]",
                            "[dim]" + truncate(todo.text, 40) + "[/]"
                    );
                });

        table.render();
        System.out.println();
    }

    private static void displaySummary(List<FileStats> fileStats,
                                       List<MethodInfo> methods, List<TodoItem> todos) {
        int totalLines = fileStats.stream().mapToInt(f -> f.lines).sum();
        int totalMethods = fileStats.stream().mapToInt(f -> f.methods).sum();
        int avgLinesPerFile = totalLines / fileStats.size();
        int avgLinesPerMethod = totalMethods > 0 ? totalLines / totalMethods : 0;

        long largeFiles = fileStats.stream().filter(f -> f.lines > 200).count();
        long longMethods = methods.stream().filter(m -> m.lines > 30).count();

        Clique.parser().print("[*green, bold]📊 Summary[/]");
        Clique.parser().print("[dim]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━[/]");
        Clique.parser().print("[cyan]Files Analyzed:[/]     [white, bold]" + fileStats.size() + "[/]");
        Clique.parser().print("[cyan]Total Lines:[/]        [white, bold]" + totalLines + "[/]");
        Clique.parser().print("[cyan]Total Methods:[/]      [white, bold]" + totalMethods + "[/]");
        Clique.parser().print("[cyan]Avg Lines/File:[/]     [white, bold]" + avgLinesPerFile + "[/]");
        Clique.parser().print("[cyan]Avg Lines/Method:[/]   [white, bold]" + avgLinesPerMethod + "[/]");
        Clique.parser().print("");

        String healthColor = (largeFiles == 0 && longMethods == 0 && todos.size() < 5) ? "green" :
                (largeFiles > 3 || longMethods > 5) ? "red" : "yellow";

        Clique.parser().print("[" + healthColor + ", bold]Code Health Indicators:[/]");
        Clique.parser().print("  [yellow]Large Files (>200):[/]    " + getHealthIndicator(largeFiles, 3));
        Clique.parser().print("  [yellow]Long Methods (>30):[/]    " + getHealthIndicator(longMethods, 5));
        Clique.parser().print("  [yellow]TODOs/FIXMEs:[/]          " + getHealthIndicator(todos.size(), 10));
        System.out.println();
    }

    // Helper methods
    private static String extractComment(String line) {
        int idx = line.indexOf("//");
        if (idx != -1) {
            return line.substring(idx + 2).trim();
        }
        return line;
    }

    private static String extractMethodName(String line) {
        line = line.replaceAll("\\s+", " ");
        int parenIdx = line.indexOf("(");
        if (parenIdx == -1) return "unknown";

        String[] parts = line.substring(0, parenIdx).trim().split(" ");
        return parts[parts.length - 1];
    }

    private static int countChar(String str, char c) {
        return (int) str.chars().filter(ch -> ch == c).count();
    }

    private static String truncate(String str, int maxLen) {
        return str.length() > maxLen ? str.substring(0, maxLen - 3) + "..." : str;
    }

    private static String getLineColor(int lines) {
        if (lines > 300) return "[red, bold]";
        if (lines > 200) return "[red]";
        if (lines > 150) return "[yellow]";
        return "[white]";
    }

    private static String getComplexityStatus(int lines, int methods) {
        if (lines > 300) return "[red, bold]⚠ Very Large[/]";
        if (lines > 200) return "[red]⚠ Large[/]";
        if (lines > 150) return "[yellow]⚡ Medium[/]";
        if (methods == 0) return "[dim]📄 No Methods[/]";
        return "[green]✓ Good[/]";
    }

    private static String getHealthIndicator(long count, int threshold) {
        if (count == 0) return "[green, bold]✓ " + count + " (Excellent)[/]";
        if (count <= threshold) return "[yellow]⚠ " + count + " (Acceptable)[/]";
        return "[red, bold]✗ " + count + " (Needs Attention)[/]";
    }
}