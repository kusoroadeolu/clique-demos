package io.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Table;
import io.github.kusoroadeolu.clique.components.Tree;
import io.github.kusoroadeolu.clique.configuration.*;
import io.github.kusoroadeolu.clique.style.ColorCode;

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
        }
    }

    private static class FileInfo {
        Path path;
        String name;
        long lines;
        long sizeKB;

        FileInfo(Path path, long lines, long size) {
            this.path = path;
            this.name = path.getFileName().toString();
            this.lines = lines;
            this.sizeKB = size / 1024;
        }
    }

    public static void main(String[] args) throws IOException {
        String projectPath = args.length > 0 ? String.join(" ", args) : ".";
        Path root = Paths.get(projectPath).toAbsolutePath().normalize();

        Map<String, FileStats> stats = new HashMap<>();
        List<FileInfo> allFiles = new ArrayList<>();
        int totalFiles = 0;

        // Scan files
        try (Stream<Path> paths = Files.walk(root)) {
            for (Path path : (Iterable<Path>) paths::iterator) {
                if (!Files.isRegularFile(path)) continue;

                String fileName = path.getFileName().toString();
                if (fileName.startsWith(".") ||
                        path.toString().contains("target") ||
                        path.toString().contains("node_modules") ||
                        path.toString().contains(".git")) continue;

                totalFiles++;
                String ext = getExtension(fileName);
                long lines = countLines(path);
                long size = Files.size(path);

                FileStats fs = stats.computeIfAbsent(ext, FileStats::new);
                fs.count++;
                fs.totalLines += lines;
                fs.totalSize += size;

                allFiles.add(new FileInfo(path, lines, size));
            }
        }

        long totalLines = stats.values().stream().mapToLong(fs -> fs.totalLines).sum();
        long totalSize  = stats.values().stream().mapToLong(fs -> fs.totalSize).sum();

        List<FileStats> sortedStats = new ArrayList<>(stats.values());
        sortedStats.sort((a, b) -> Integer.compare(b.count, a.count));

        // ── Header ──────────────────────────────────────────────────────────
        Clique.frame(BoxType.DOUBLE_LINE, ColorCode.CYAN)
                .title("[*cyan, bold]PROJECT FILE EXPLORER[/]", FrameAlign.CENTER)
                .nest("[dim]Path:[/] [yellow]" + root + "[/]", FrameAlign.LEFT)
                .nest("[dim]Files found:[/] [white, bold]" + totalFiles + "[/]", FrameAlign.LEFT)
                .render();

        System.out.println();

        // ── Files by Type ────────────────────────────────────────────────────
        Table typeTable = Clique.table(TableType.BOX_DRAW,
                        TableConfiguration.builder()
                                .alignment(CellAlign.LEFT)
                                .padding(2)
                                .build())
                .headers("[cyan, bold]Extension[/]", "[cyan, bold]Files[/]",
                        "[cyan, bold]Lines[/]", "[cyan, bold]Size (KB)[/]");

        for (FileStats fs : sortedStats) {
            String color = getExtensionColor(fs.extension);
            typeTable.row(
                    "[" + color + "]" + fs.extension + "[/]",
                    "[white]" + fs.count + "[/]",
                    "[yellow]" + fs.totalLines + "[/]",
                    "[dim]" + (fs.totalSize / 1024) + "[/]"
            );
        }

        Clique.frame(ColorCode.GREEN)
                .title("[*green, bold]📊 Files by Type[/]", FrameAlign.LEFT)
                .nest(typeTable)
                .render();

        System.out.println();

        // ── Top 5 Largest Files as a Tree ───────────────────────────────────
        allFiles.sort((a, b) -> Long.compare(b.lines, a.lines));
        List<FileInfo> top5 = allFiles.subList(0, Math.min(5, allFiles.size()));

        TreeConfiguration treeConfig = TreeConfiguration.builder()
                .connectorColor("magenta")
                .build();

        Tree largestTree = Clique.tree("[*magenta, bold]Top Files by Lines[/]", treeConfig);

        for (int i = 0; i < top5.size(); i++) {
            FileInfo fi = top5.get(i);

            // Show the parent dir relative to root for context
            Path rel = root.relativize(fi.path);
            String parentDir = rel.getParent() != null
                    ? "[dim]" + rel.getParent() + "/[/]"
                    : "[dim].[/]";

            String ext = getExtension(fi.name);
            String fileColor = getExtensionColor(ext);
            String rank = i == 0 ? "[*yellow]#" + (i + 1) + "[/]" : "[dim]#" + (i + 1) + "[/]";

            Tree fileNode = largestTree.add(
                    rank + " [" + fileColor + ", bold]" + fi.name + "[/]"
                            + "  [dim]" + fi.lines + " lines · " + fi.sizeKB + " KB[/]"
            );
            fileNode.add(parentDir);
        }

        Clique.frame(ColorCode.MAGENTA)
                .title("[*magenta, bold]📈 Largest Files[/]", FrameAlign.LEFT)
                .nest(largestTree)
                .render();

        System.out.println();

        // ── Summary ──────────────────────────────────────────────────────────
        Table summaryTable = Clique.table(TableType.BOX_DRAW,
                        TableConfiguration.builder()
                                .alignment(CellAlign.LEFT)
                                .build())
                .headers("[cyan, bold]Metric[/]", "[cyan, bold]Value[/]")
                .row("[green]Total Files[/]",  "[white, bold]" + totalFiles + "[/]")
                .row("[green]Total Lines[/]",  "[white, bold]" + totalLines + "[/]")
                .row("[green]Total Size[/]",   "[white, bold]" + (totalSize / 1024) + " KB[/]")
                .row("[green]File Types[/]",   "[white, bold]" + stats.size() + "[/]");

        Clique.frame(ColorCode.BLUE)
                .title("[*blue, bold]📝 Summary[/]", FrameAlign.LEFT)
                .nest(summaryTable)
                .render();

        System.out.println();
    }

    private static String getExtension(String fileName) {
        int idx = fileName.lastIndexOf('.');
        return idx > 0 ? fileName.substring(idx) : "no ext";
    }

    private static long countLines(Path path) {
        try {
            return Files.lines(path).count();
        } catch (IOException | UncheckedIOException e) {
            return 0;
        }
    }

    private static String getExtensionColor(String ext) {
        return switch (ext) {
            case ".java"                              -> "*blue";
            case ".xml", ".json", ".yaml", ".yml"   -> "magenta";
            case ".md", ".txt"                       -> "green";
            case ".properties", ".conf"              -> "yellow";
            case ".sh", ".bat"                       -> "red";
            default                                  -> "white";
        };
    }
}