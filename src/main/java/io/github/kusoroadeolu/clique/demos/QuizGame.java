package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Box;
import io.github.kusoroadeolu.clique.components.Table;
import io.github.kusoroadeolu.clique.configuration.*;
import io.github.kusoroadeolu.clique.style.ColorCode;

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

        displayWelcome();

        int score = 0;
        List<String> results = new ArrayList<>();

        for (int i = 0; i < QUESTIONS.size(); i++) {
            Question q = QUESTIONS.get(i);

            displayQuestion(q, i + 1);

            int answer = getUserAnswer(scanner);
            boolean correct = (answer - 1) == q.correctAnswer;

            if (correct) {
                score++;
                results.add("[green, bold]✓[/]");
            } else {
                results.add("[red, bold]✗[/]");
            }

            displayFeedback(correct);
            displayScore(score, i + 1);

            if (i < QUESTIONS.size() - 1) {
                Clique.parser().print("\n[dim]Press Enter for next question...[/]");
                scanner.nextLine();
            }
        }

        displayFinalResults(score, results);
        scanner.close();
    }

    private static void displayWelcome() {
        // Use a real Box instead of hand-drawn ASCII borders
        Box titleBox = Clique.box(BoxType.DOUBLE_LINE, ColorCode.BRIGHT_CYAN)
                .content("[*cyan, bold]JAVA KNOWLEDGE QUIZ");

        // Frame wraps the title box with a welcome message underneath
        Clique.frame(BoxType.ROUNDED, ColorCode.CYAN)
                .nest(titleBox)
                .nest("")
                .nest("[*green]Welcome to the quiz![/] Test your Java knowledge.")
                .nest("[yellow]" + QUESTIONS.size() + " questions. Good luck![/]")
                .nest("")
                .render();

        System.out.println();
    }

    private static void displayQuestion(Question q, int number) {
        System.out.println();

        // Question table
        Table questionTable = Clique.table(TableType.ROUNDED_BOX_DRAW,
                        TableConfiguration.builder()
                                .borderColor(ColorCode.BLUE)
                                .padding(2)
                                .alignment(CellAlign.LEFT)
                                .build())
                .headers("[*yellow, bold]Question[/]")
                .row("[white]" + q.question + "[/]");

        // Options table
        Table optionsTable = Clique.table(TableType.COMPACT,
                        TableConfiguration.builder()
                                .borderColor(ColorCode.CYAN)
                                .padding(2)
                                .alignment(CellAlign.LEFT)
                                .build())
                .headers("[cyan, bold]#[/]", "[cyan, bold]Answer[/]");

        for (int i = 0; i < q.options.length; i++) {
            optionsTable.row(
                    "[*green, bold]" + (i + 1) + "[/]",
                    "[white]" + q.options[i] + "[/]"
            );
        }

        // Frame wraps both tables together — this is exactly what Frame is for
        Clique.frame(ColorCode.MAGENTA)
                .title("[*white, bold]Question " + number + "/" + QUESTIONS.size()
                        + "[/] [dim]│[/] [cyan]" + q.category + "[/]", FrameAlign.LEFT)
                .nest(questionTable)
                .nest(optionsTable)
                .render();

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
        System.out.println();
        Clique.table(ColorCode.BRIGHT_YELLOW)
                .headers("[*yellow, bold]Current Score[/]")
                .row("[*white, bold]" + score + " / " + questionNumber + "[/]")
                .render();
    }

    private static void displayFinalResults(int score, List<String> results) {
        System.out.println("\n");

        double percentage = (score * 100.0) / QUESTIONS.size();

        // Summary stats table
        Table summaryTable = Clique.table(TableType.BOX_DRAW,
                        TableConfiguration.builder()
                                .borderColor(ColorCode.BRIGHT_MAGENTA)
                                .padding(2)
                                .build())
                .headers("[*cyan, bold]Metric[/]", "[*cyan, bold]Value[/]")
                .row("[yellow]Questions[/]", "[white, bold]" + QUESTIONS.size() + "[/]")
                .row("[yellow]Correct[/]", "[green, bold]" + score + "[/]")
                .row("[yellow]Incorrect[/]", "[red, bold]" + (QUESTIONS.size() - score) + "[/]")
                .row("[yellow]Score[/]", getScoreColor(percentage) + String.format("%.0f%%", percentage) + "[/]");

        // Per-question breakdown table
        Table breakdownTable = Clique.table(TableType.MARKDOWN,
                        TableConfiguration.builder()
                                .borderColor(ColorCode.BLUE)
                                .alignment(CellAlign.LEFT)
                                .build())
                .headers("[cyan, bold]Q#[/]", "[cyan, bold]Category[/]", "[cyan, bold]Result[/]");

        for (int i = 0; i < QUESTIONS.size(); i++) {
            breakdownTable.row(
                    "[white]" + (i + 1) + "[/]",
                    "[dim]" + QUESTIONS.get(i).category + "[/]",
                    results.get(i)
            );
        }

        // Grade box
        Box gradeBox = Clique.box(BoxType.ROUNDED, getGradeColor(percentage))
                .content(getGradeMessage(percentage), TextAlign.CENTER);

        // One Frame to rule them all
        Clique.frame(BoxType.DOUBLE_LINE, ColorCode.BRIGHT_CYAN)
                .title("[*cyan, bold]Quiz Complete![/]", FrameAlign.CENTER)
                .nest(summaryTable, FrameAlign.CENTER)
                .nest("")
                .nest(breakdownTable, FrameAlign.CENTER)
                .nest("")
                .nest(gradeBox, FrameAlign.CENTER)
                .render();

        System.out.println();
    }

    private static String getScoreColor(double percentage) {
        if (percentage >= 80) return "[*green, bold]";
        if (percentage >= 60) return "[*yellow, bold]";
        return "[*red, bold]";
    }

    private static ColorCode getGradeColor(double percentage) {
        if (percentage >= 80) return ColorCode.BRIGHT_GREEN;
        if (percentage >= 60) return ColorCode.BRIGHT_YELLOW;
        return ColorCode.BRIGHT_RED;
    }

    private static String getGradeMessage(double percentage) {
        if (percentage == 100) return "[*green, bold]PERFECT SCORE! Outstanding![/]";
        if (percentage >= 80)  return "[*green, bold]Excellent work! You really know your stuff![/]";
        if (percentage >= 60)  return "[*yellow, bold]Good job! Keep learning![/]";
        return "[*red, bold]Keep studying! You'll get better![/]";
    }
}