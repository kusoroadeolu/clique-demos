package com.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.ansi.ColorCode;
import io.github.kusoroadeolu.clique.config.BorderStyle;
import io.github.kusoroadeolu.clique.config.CellAlign;
import io.github.kusoroadeolu.clique.config.TableConfiguration;
import io.github.kusoroadeolu.clique.tables.CustomizableTable;
import io.github.kusoroadeolu.clique.tables.Table;
import io.github.kusoroadeolu.clique.tables.TableType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuizGame {

    private static class Question {
        String question;
        String[] options;
        int correctAnswer;
        String category;

        Question(String question, String[] options, int correctAnswer, String category) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
            this.category = category;
        }
    }

    private static final List<Question> QUESTIONS = Arrays.asList(
            new Question(
                    "What does JVM stand for?",
                    new String[]{"Java Virtual Machine", "Java Variable Method", "Just Very Modern", "Java Verified Module"},
                    0,
                    "Java Basics"
            ),
            new Question(
                    "Which keyword is used to prevent method overriding?",
                    new String[]{"static", "final", "const", "sealed"},
                    1,
                    "Java OOP"
            ),
            new Question(
                    "What is the time complexity of binary search?",
                    new String[]{"O(n)", "O(log n)", "O(n²)", "O(1)"},
                    1,
                    "Algorithms"
            ),
            new Question(
                    "Which of these is NOT a SOLID principle?",
                    new String[]{"Single Responsibility", "Open/Closed", "DRY Principle", "Dependency Inversion"},
                    2,
                    "Design Patterns"
            ),
            new Question(
                    "What does ANSI stand for?",
                    new String[]{"American National Standards Institute", "Advanced Network System Interface", "Automatic Number System Identifier", "Applied Network Standards"},
                    0,
                    "General"
            )
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome screen
        displayWelcome();

        int score = 0;
        List<String> results = new ArrayList<>();

        // Ask questions
        for (int i = 0; i < QUESTIONS.size(); i++) {
            Question q = QUESTIONS.get(i);

            displayQuestion(q, i + 1);

            int answer = getUserAnswer(scanner);
            boolean correct = (answer - 1) == q.correctAnswer;

            if (correct) {
                score++;
                displayFeedback(true);
                results.add("✓");
            } else {
                displayFeedback(false);
                results.add("✗");
            }

            displayScore(score, i + 1);

            if (i < QUESTIONS.size() - 1) {
                Clique.parser().print("\n[dim]Press Enter for next question...[/]");
                scanner.nextLine();
            }
        }

        // Final results
        displayFinalResults(score, results);

        scanner.close();
    }

    private static void displayWelcome() {
        Clique.parser().print("\n[*cyan, bold]╔════════════════════════════════════════╗[/]");
        Clique.parser().print("[*cyan, bold]║     JAVA KNOWLEDGE QUIZ 🎯            ║[/]");
        Clique.parser().print("[*cyan, bold]╚════════════════════════════════════════╝[/]\n");

        Clique.parser().print("[*green]Welcome to the quiz![/] Test your Java knowledge.\n");
        Clique.parser().print("[yellow]There are " + QUESTIONS.size() + " questions. Good luck![/]\n");
    }

    private static void displayQuestion(Question q, int number) {
        System.out.println();
        Clique.parser().print("[*magenta, bold]═══════════════════════════════════════════════[/]");
        Clique.parser().print("[*white, bold]Question " + number + "/" + QUESTIONS.size() + "[/] [dim]│ Category: [/][cyan]" + q.category + "[/]");
        Clique.parser().print("[*magenta, bold]═══════════════════════════════════════════════[/]\n");

        // Use rounded box table for the question
        BorderStyle questionStyle = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.BRIGHT_BLUE)
                .verticalBorderStyles(ColorCode.BRIGHT_BLUE)
                .edgeBorderStyles(ColorCode.BRIGHT_CYAN)
                .build();

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .borderStyle(questionStyle)
                .parser(Clique.parser())
                .padding(2)
                .alignment(CellAlign.LEFT)
                .build();

        Table questionTable = Clique.table(TableType.ROUNDED_BOX_DRAW, config)
        .addHeaders("[*yellow, bold]Question[/]");
        questionTable.addRows("[white]" + q.question + "[/]");
        questionTable.render();

        System.out.println();

        // Use compact table for options
        BorderStyle optionsStyle = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.CYAN)
                .build();

        TableConfiguration optionsConfig = TableConfiguration.immutableBuilder()
                .borderStyle(optionsStyle)
                .parser(Clique.parser())
                .padding(3)
                .alignment(CellAlign.LEFT)
                .build();

        Table optionsTable = Clique.table(TableType.COMPACT, optionsConfig)
        .addHeaders("[cyan, bold]#[/]", "[cyan, bold]Answer[/]");

        for (int i = 0; i < q.options.length; i++) {
            optionsTable.addRows(
                    "[*green, bold]" + (i + 1) + "[/]",
                    "[white]" + q.options[i] + "[/]"
            );
        }

        optionsTable.render();
        System.out.println();
    }

    private static int getUserAnswer(Scanner scanner) {
        while (true) {
            Clique.parser().print("[*white, bold]Your answer (1-4):[/] ");
            try {
                String input = scanner.nextLine().trim();
                int answer = Integer.parseInt(input);
                if (answer >= 1 && answer <= 4) {
                    return answer;
                }
                Clique.parser().print("[red]Please enter a number between 1 and 4[/]");
            } catch (NumberFormatException e) {
                Clique.parser().print("[red]Invalid input! Please enter a number.[/]");
            }
        }
    }

    private static void displayFeedback(boolean correct) {
        System.out.println();
        if (correct) {
            Clique.parser().print("[*green, bold]✓ Correct![/] [green]Well done![/]");
        } else {
            Clique.parser().print("[*red, bold]✗ Incorrect![/] [red]Better luck next time![/]");
        }
    }

    private static void displayScore(int score, int questionNumber) {
        // Use default table with custom styling
        BorderStyle scoreStyle = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.YELLOW)
                .verticalBorderStyles(ColorCode.YELLOW)
                .edgeBorderStyles(ColorCode.BRIGHT_YELLOW)
                .build();

        TableConfiguration config = TableConfiguration.immutableBuilder()
                .borderStyle(scoreStyle)
                .parser(Clique.parser())
                .padding(2)
                .alignment(CellAlign.CENTER)
                .build();


        CustomizableTable scoreTable = Clique.customizableTable(TableType.DEFAULT, config)
                .addHeaders("[*yellow, bold]Current Score[/]")
                .customizeHorizontalLine('═')
                .customizeVerticalLine('║');

        scoreTable.addRows("[*white, bold]" + score + " / " + questionNumber + "[/]");
        scoreTable.render();

    }

    private static void displayFinalResults(int score, List<String> results) {
        System.out.println("\n\n");
        Clique.parser().print("[*magenta, bold]╔════════════════════════════════════════╗[/]");
        Clique.parser().print("[*magenta, bold]║         QUIZ COMPLETE! 🎉             ║[/]");
        Clique.parser().print("[*magenta, bold]╚════════════════════════════════════════╝[/]\n");

        double percentage = (score * 100.0) / QUESTIONS.size();

        // Final score with box draw table
        BorderStyle finalStyle = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.BRIGHT_MAGENTA)
                .verticalBorderStyles(ColorCode.BRIGHT_MAGENTA)
                .edgeBorderStyles(ColorCode.BRIGHT_CYAN)
                .build();

        TableConfiguration finalConfig = TableConfiguration.immutableBuilder()
                .borderStyle(finalStyle)
                .parser(Clique.parser())
                .padding(3)
                .alignment(CellAlign.CENTER)
                .build();


        Table finalTable = Clique.table(TableType.BOX_DRAW, finalConfig)
                .addHeaders("[*cyan, bold]Metric[/]", "[*cyan, bold]Value[/]");
        finalTable.addRows(
                "[yellow]Questions[/]",
                "[white, bold]" + QUESTIONS.size() + "[/]"
        );
        finalTable.addRows(
                "[yellow]Correct[/]",
                "[green, bold]" + score + "[/]"
        );
        finalTable.addRows(
                "[yellow]Incorrect[/]",
                "[red, bold]" + (QUESTIONS.size() - score) + "[/]"
        );
        finalTable.addRows(
                "[yellow]Score[/]",
                getScoreColor(percentage) + String.format("%.0f%%", percentage) + "[/]"
        );
        finalTable.render();

        System.out.println();

        // Question breakdown with markdown table
        BorderStyle breakdownStyle = BorderStyle.immutableBuilder()
                .horizontalBorderStyles(ColorCode.CYAN)
                .verticalBorderStyles(ColorCode.BLUE)
                .build();

        TableConfiguration breakdownConfig = TableConfiguration.immutableBuilder()
                .borderStyle(breakdownStyle)
                .parser(Clique.parser())
                .padding(2)
                .alignment(CellAlign.LEFT)
                .build();

        Table breakdownTable = Clique.table(TableType.MARKDOWN, breakdownConfig)
                .addHeaders("[cyan, bold]Q#[/]", "[cyan, bold]Category[/]", "[cyan, bold]Result[/]");

        for (int i = 0; i < QUESTIONS.size(); i++) {
            Question q = QUESTIONS.get(i);
            String result = results.get(i);
            String resultColor = result.equals("✓") ? "green" : "red";

            breakdownTable.addRows(
                    "[white]" + (i + 1) + "[/]",
                    "[dim]" + q.category + "[/]",
                    "[" + resultColor + ", bold]" + result + "[/]"
            );
        }

        breakdownTable.render();

        // Final message
        System.out.println();
        displayGrade(percentage);
    }

    private static String getScoreColor(double percentage) {
        if (percentage >= 80) return "[*green, bold]";
        if (percentage >= 60) return "[*yellow, bold]";
        return "[*red, bold]";
    }

    private static void displayGrade(double percentage) {
        if (percentage == 100) {
            Clique.parser().print("[*green, bold]🏆 PERFECT SCORE! Outstanding![/]");
        } else if (percentage >= 80) {
            Clique.parser().print("[*green, bold]⭐ Excellent work! You really know your stuff![/]");
        } else if (percentage >= 60) {
            Clique.parser().print("[*yellow, bold]👍 Good job! Keep learning![/]");
        } else {
            Clique.parser().print("[*red, bold]📚 Keep studying! You'll get better![/]");
        }
        System.out.println();
    }
}