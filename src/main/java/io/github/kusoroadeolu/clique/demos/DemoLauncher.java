package io.github.kusoroadeolu.clique.demos;

import java.io.IOException;

public class DemoLauncher {

    public static void runThemeShowcase() {
        ThemeShowcase.main(new String[]{});
    }

    public static void runQuizGame() {
        QuizGame.main(new String[]{});
    }

    public static void runCodeScanner(String projectPath) {
        CodeScanner.main(new String[]{projectPath});
    }

    public static void runBookReviewDemo(){
        CodeScanner.main(new String[]{});
    }

    public static void runBuildPipeline(){
        try {
            BuildPipeline.main(new String[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runCliArtGallery(){
        try {
            CliArtGallery.main(new String[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runLiveDashboard(){
        try {
            LiveDashboard.main(new String[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runMusicVisualizer(){
        try {
            MusicVisualizer.main(new String[0]);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void runProjectExplorer(String projectPath) {
        try {
            ProjectExplorer.main(new String[]{projectPath});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runBootScreen(){
        try {
            BootScreen.main(new String[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}