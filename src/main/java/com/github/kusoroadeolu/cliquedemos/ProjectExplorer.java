package com.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.ansi.ColorCode;
import io.github.kusoroadeolu.clique.boxes.BoxType;
import io.github.kusoroadeolu.clique.config.BorderStyle;
import io.github.kusoroadeolu.clique.config.BoxConfiguration;
import io.github.kusoroadeolu.clique.config.CellAlign;
import io.github.kusoroadeolu.clique.config.TableConfiguration;
import io.github.kusoroadeolu.clique.tables.Table;
import io.github.kusoroadeolu.clique.tables.TableType;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProjectExplorer {

    private static class FileStats {
        String extension;
        int count;
        long totalLines;
        long totalSize;

        FileStats(String ext) {
            this.extension = ext;
            this.count = 0;
            this.totalLines = 0;
            this.totalSize = 0;
        }
    }

    private static class FileInfo {
        String name;
        long lines;
        long sizeKB;

        FileInfo(String name, long lines, long size) {
            this.name = name;
            this.lines = lines;
            this.sizeKB = size / 1024;
        }
    }

    public static void main(String[] args) throws IOException {
        String projectPath = args.length > 0 ? args[0] : ".";
        Path root = Paths.get(projectPath);
        BorderStyle style = BorderStyle
                .immutableBuilder().edgeBorderStyles(ColorCode.CYAN).horizontalBorderStyles(ColorCode.CYAN).verticalBorderStyles(ColorCode.CYAN).build();

        // Print header
        Clique.box(BoxType.DOUBLE_LINE, BoxConfiguration.immutableBuilder().autoSize().borderStyle(style).build())
                .noDimensions()
                .content("[yellow]PROJECT FILE EXPLORER")
                .render();

        Clique.parser().print("[dim]Scanning:[/] [yellow]" + root.toAbsolutePath() + "[/]\n");

        Map<String, FileStats> stats = new HashMap<>();
        List<FileInfo> largestFiles = new ArrayList<>();
        int totalFiles = 0;

        // Scan files
        try (Stream<Path> paths = Files.walk(root)) {
            for (Path path : (Iterable<Path>) paths::iterator) {
                if (Files.isRegularFile(path)) {
                    String fileName = path.getFileName().toString();

                    // Skip hidden files and common ignore patterns
                    if (fileName.startsWith(".") ||
                            path.toString().contains("target") ||
                            path.toString().contains("node_modules") ||
                            path.toString().contains(".git")) {
                        continue;
                    }

                    totalFiles++;
                    String ext = getExtension(fileName);
                    long lines = countLines(path);
                    long size = Files.size(path);

                    stats.computeIfAbsent(ext, FileStats::new);
                    FileStats fs = stats.get(ext);
                    fs.count++;
                    fs.totalLines += lines;
                    fs.totalSize += size;

                    largestFiles.add(new FileInfo(fileName, lines, size));
                }
            }
        }

        // Sort and display stats
        List<FileStats> sortedStats = new ArrayList<>(stats.values());
        sortedStats.sort((a, b) -> Integer.compare(b.count, a.count));

        // Files by type table
        Clique.parser().print("[*green, bold]📊 Files by Type[/]\n");

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .parser(Clique.parser())
                .alignment(CellAlign.LEFT)
                .padding(2)
                .build();

        Table typeTable = Clique.table(TableType.BOX_DRAW, config)
                .addHeaders("[cyan, bold]Extension[/]", "[cyan, bold]Files[/]", "[cyan, bold]Lines[/]", "[cyan, bold]Size (KB)[/]");

        for (FileStats fs : sortedStats) {
            String extColor = getExtensionColor(fs.extension);
            typeTable.addRows(
                    "[" + extColor + "]" + fs.extension + "[/]",
                    "[white]" + fs.count + "[/]",
                    "[yellow]" + fs.totalLines + "[/]",
                    "[dim]" + (fs.totalSize / 1024) + "[/]"
            );
        }

        typeTable.render();

        // Largest files table
        largestFiles.sort((a, b) -> Long.compare(b.lines, a.lines));
        List<FileInfo> top5 = largestFiles.subList(0, Math.min(5, largestFiles.size()));

        Clique.parser().print("\n[*magenta, bold]📈 Largest Files (by lines)[/]\n");

        Table largeTable = Clique.table(TableType.MARKDOWN, config)
                .addHeaders("[cyan, bold]File[/]", "[cyan, bold]Lines[/]", "[cyan, bold]Size (KB)[/]");

        for (FileInfo fi : top5) {
            String truncated = fi.name.length() > 40 ? fi.name.substring(0, 37) + "..." : fi.name;
            largeTable.addRows(
                    "[white]" + truncated + "[/]",
                    "[yellow]" + fi.lines + "[/]",
                    "[dim]" + fi.sizeKB + "[/]"
            );
        }

        largeTable.render();

        // Summary
        long totalLines = sortedStats.stream().mapToLong(fs -> fs.totalLines).sum();
        long totalSize = sortedStats.stream().mapToLong(fs -> fs.totalSize).sum();

        Clique.parser().print("\n[*blue, bold]📝 Summary[/]");
        Clique.parser().print("[dim]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━[/]");
        Clique.parser().print("[green]Total Files:[/]  [white, bold]" + totalFiles + "[/]");
        Clique.parser().print("[green]Total Lines:[/]  [white, bold]" + totalLines + "[/]");
        Clique.parser().print("[green]Total Size:[/]   [white, bold]" + (totalSize / 1024) + " KB[/]");
        Clique.parser().print("[green]File Types:[/]   [white, bold]" + stats.size() + "[/]\n");
    }

    private static String getExtension(String fileName) {
        int idx = fileName.lastIndexOf('.');
        return idx > 0 ? fileName.substring(idx) : "no ext";
    }

    private static long countLines(Path path) {
        try {
            return Files.lines(path).count();
        } catch (IOException | UncheckedIOException e) {
            // Skip binary files or files with encoding issues
            return 0;
        }
    }

    private static String getExtensionColor(String ext) {
        return switch (ext) {
            case ".java" -> "*blue";
            case ".xml", ".json", ".yaml", ".yml" -> "magenta";
            case ".md", ".txt" -> "green";
            case ".properties", ".conf" -> "yellow";
            case ".sh", ".bat" -> "red";
            default -> "white";
        };
    }
}