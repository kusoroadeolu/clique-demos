package com.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.spi.AnsiCode;

//A semantic colors demo mimicking API output in a real world env
public class SemanticColorsDemo {

    private static class RGBColor implements AnsiCode {
        private final String code;

        public RGBColor(int r, int g, int b, boolean isBackground) {
            int type = isBackground ? 48 : 38;
            this.code = String.format("\u001B[%d;2;%d;%d;%dm", type, r, g, b);
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public static class AppColors {
        public static final AnsiCode ERROR = new RGBColor(191, 97, 106, false);     // Red
        public static final AnsiCode SUCCESS = new RGBColor(163, 190, 140, false);   // Green
        public static final AnsiCode WARNING = new RGBColor(235, 203, 139, false);   // Yellow
        public static final AnsiCode INFO = new RGBColor(136, 192, 208, false);      // Cyan
        public static final AnsiCode DEBUG = new RGBColor(180, 142, 173, false);     // Purple
        public static final AnsiCode MUTED = new RGBColor(216, 222, 233, false);     // Light gray
    }

    public static void main(String[] args) {
        // Register semantic colors
        setupSemanticColors();

        Clique.parser().print("[bold]📋 Application Log Viewer[/]");
        Clique.parser().print("[muted]Using semantic color naming for consistent styling[/]\n");

        // Simulate application logs
        showApplicationLogs();


        // Show deployment status
        showDeploymentStatus();


        // Show system monitoring
        showSystemMonitoring();
    }

    private static void setupSemanticColors() {
        Clique.registerStyle("error", AppColors.ERROR);
        Clique.registerStyle("success", AppColors.SUCCESS);
        Clique.registerStyle("warning", AppColors.WARNING);
        Clique.registerStyle("info", AppColors.INFO);
        Clique.registerStyle("debug", AppColors.DEBUG);
        Clique.registerStyle("muted", AppColors.MUTED);
    }

    private static void showApplicationLogs() {
        Clique.parser().print("[bold, ul]Application Logs[/]\n");

        log("info", "Application started successfully");
        log("debug", "Loading configuration from config.yml");
        log("success", "Database connection established");
        log("info", "Initializing web server on port 8080");
        log("warning", "Deprecated API endpoint /v1/users still in use");
        log("error", "Failed to connect to cache server: Connection timeout");
        log("info", "Retrying connection...");
        log("success", "Cache server connection established");
        log("info", "Application ready to handle requests");
    }

    private static void showDeploymentStatus() {
        Clique.parser().print("[bold, ul]Deployment Status[/]\n");

        deploymentStep("Build", "success", "Completed in 45s");
        deploymentStep("Tests", "success", "98 passed, 0 failed");
        deploymentStep("Security Scan", "warning", "2 low-priority vulnerabilities found");
        deploymentStep("Docker Build", "success", "Image tagged: app:v2.1.0");
        deploymentStep("Deploy to Staging", "success", "Deployed successfully");
        deploymentStep("Smoke Tests", "success", "All checks passed");
        deploymentStep("Deploy to Production", "error", "Insufficient permissions");
    }

    private static void showSystemMonitoring() {
        Clique.parser().print("[bold, ul]System Health Check[/]\n");

        healthCheck("API Server", "success", "Healthy", "Response time: 45ms");
        healthCheck("Database", "success", "Healthy", "Connections: 12/100");
        healthCheck("Cache", "warning", "Degraded", "Memory usage: 87%");
        healthCheck("Queue", "success", "Healthy", "Pending jobs: 3");
        healthCheck("Storage", "warning", "Degraded", "Disk usage: 92%");
        healthCheck("Email Service", "error", "Down", "Connection refused");
    }

    private static void log(String level, String message) {
        String timestamp = getCurrentTimestamp();
        String icon = getLogIcon(level);

        Clique.parser().print("[muted]" + timestamp + "[/] ");
        Clique.parser().print("[" + level + "]" + icon + " " + level.toUpperCase() + "[/] ");
        Clique.parser().print(message + "\n");
    }

    private static void deploymentStep(String step, String status, String details) {
        String icon = getStatusIcon(status);

        Clique.parser().print("[" + status + "]" + icon + "[/] ");
        Clique.parser().print("[bold]" + step + "[/]: ");
        Clique.parser().print("[" + status + "]" + details + "[/]\n");
    }

    private static void healthCheck(String service, String status, String state, String details) {
        String icon = getStatusIcon(status);

        Clique.parser().print("[" + status + "]" + icon + "[/] ");
        Clique.parser().print("[bold]" + padRight(service, 20) + "[/] ");
        Clique.parser().print("[" + status + "]" + padRight(state, 10) + "[/] ");
        Clique.parser().print("[muted]" + details + "[/]\n");
    }

    private static String getLogIcon(String level) {
        return switch (level) {
            case "error" -> "✗";
            case "success" -> "✓";
            case "warning" -> "⚠";
            case "info" -> "ℹ";
            case "debug" -> "⚙";
            default -> "•";
        };
    }

    private static String getStatusIcon(String status) {
        return switch (status) {
            case "success" -> "✓";
            case "error" -> "✗";
            case "warning" -> "⚠";
            default -> "•";
        };
    }

    private static String getCurrentTimestamp() {
        return java.time.LocalTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}