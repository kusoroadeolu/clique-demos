package io.github.kusoroadeolu.cliquedemos;

import java.io.IOException;

public class DemoLauncher {

    public static void runThemeShowcase() {
        ThemeShowcase.main(new String[]{});
    }

    public static void runSemanticColorsDemo() {
        SemanticColorsDemo.main(new String[]{});
    }

    public static void runQuizGame() {
        QuizGame.main(new String[]{});
    }

//    public static void runCodeScanner(String projectPath) {
//        CodeScanner.main(new String[]{projectPath});
//    }

    public static void runProjectExplorer(String projectPath) {
        try {
            ProjectExplorer.main(new String[]{projectPath});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}