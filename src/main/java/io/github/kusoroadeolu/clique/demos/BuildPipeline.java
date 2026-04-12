package io.github.kusoroadeolu.clique.demos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.components.Tree;
import io.github.kusoroadeolu.clique.configuration.*;
import io.github.kusoroadeolu.clique.themes.CatppuccinMochaTheme;

import java.util.List;

public class BuildPipeline {

    public static void main(String[] args) throws InterruptedException {

        // Register theme for that rich visual feel
        Clique.registerTheme(new CatppuccinMochaTheme());

        // ─── HEADER ────────────────────────────────────────────────────────────
        Clique.frame(BoxType.DOUBLE_LINE, "ctp_mauve")
                .title("[ctp_mauve, bold]⚙  BUILD PIPELINE[/]  [ctp_overlay0]v2.4.1[/]")
                .nest("[ctp_subtext1]Project:[/]  [ctp_text, bold]my-awesome-app[/]")
                .nest("[ctp_subtext1]Branch:[/]   [ctp_green]main[/]  [ctp_overlay0]·[/]  [ctp_subtext1]Commit:[/] [ctp_sky]a3f92bc[/]  [ctp_overlay0]·[/]  [ctp_subtext1]Triggered by:[/] [ctp_peach]push[/]", FrameAlign.CENTER)
                .render();

        System.out.println();
        sleep(400);

        // ─── STEP 1: RESOLVING DEPENDENCIES ────────────────────────────────────
        Clique.parser().print("[ctp_overlay0]━━━  [ctp_blue, bold]STEP 1[/][ctp_overlay0]  Resolving Dependencies  ━━━[/]");
        System.out.println();

        ProgressBarConfiguration resolveConfig = ProgressBarConfiguration
                .fromPreset(ProgressBarPreset.DOTS)
                .format("[ctp_blue]:bar[/]  [ctp_subtext1]:percent%[/]  [ctp_overlay0]resolving...[/]")
                .build();

        var resolveBar = Clique.progressBar(30, resolveConfig);
        for (int i = 0; i < 30; i++) {
            resolveBar.tick();
            sleep(40);
        }

        System.out.println();
        Clique.list()
                .item(Clique.ink().green().on("✓"), "[ctp_text]clique-core[/]          [ctp_overlay0]4.0.0[/]")
                .item(Clique.ink().green().on("✓"), "[ctp_text]clique-themes[/]         [ctp_overlay0]1.0.1[/]")
                .item(Clique.ink().green().on("✓"), "[ctp_text]jackson-databind[/]       [ctp_overlay0]2.16.1[/]")
                .item(Clique.ink().green().on("✓"), "[ctp_text]slf4j-api[/]              [ctp_overlay0]2.0.9[/]")
                .item(Clique.ink().yellow().on("↓"), "[ctp_yellow]commons-lang3[/]          [ctp_overlay0]3.14.0  [ctp_yellow](downloading)[/]")
                .render();

        System.out.println();
        Clique.parser().print(
                Clique.ink().green().bold().on("✓") + " [ctp_green]5 dependencies resolved[/]  [ctp_overlay0](1 downloaded, 4 cached)[/]"
        );
        System.out.println();
        sleep(300);

        // ─── STEP 2: COMPILATION ───────────────────────────────────────────────
        Clique.parser().print("[ctp_overlay0]━━━  [ctp_peach, bold]STEP 2[/][ctp_overlay0]  Compilation  ━━━[/]");
        System.out.println();

        // Module tree

        Tree projectTree = Clique.tree("[ctp_mauve, bold]my-awesome-app/", "ctp_overlay0");

        Tree coreModule = projectTree.add("[ctp_blue, bold]core/");
        coreModule.add("[ctp_green]✓[/]  [ctp_text]AuthService.java        [ctp_overlay0]compiled  (312 lines)[/]");
        coreModule.add("[ctp_green]✓[/]  [ctp_text]UserRepository.java     [ctp_overlay0]compiled  (198 lines)[/]");
        coreModule.add("[ctp_green]✓[/]  [ctp_text]SessionManager.java     [ctp_overlay0]compiled  (245 lines)[/]");
        coreModule.add("[ctp_yellow]⚠[/]  [ctp_text]ConfigLoader.java       [ctp_yellow]1 warning[/]");

        Tree apiModule = projectTree.add("[ctp_blue, bold]api/");
        apiModule.add("[ctp_green]✓[/]  [ctp_text]UserController.java     [ctp_overlay0]compiled  (187 lines)[/]");
        apiModule.add("[ctp_green]✓[/]  [ctp_text]AuthController.java     [ctp_overlay0]compiled  (143 lines)[/]");
        apiModule.add("[ctp_red]✗[/]  [ctp_text]PaymentController.java  [ctp_red]compilation error[/]");

        Tree utilModule = projectTree.add("[ctp_blue, bold]util/");
        utilModule.add("[ctp_green]✓[/]  [ctp_text]StringUtils.java        [ctp_overlay0]compiled  (89 lines)[/]");
        utilModule.add("[ctp_green]✓[/]  [ctp_text]DateUtils.java          [ctp_overlay0]compiled  (67 lines)[/]");

        projectTree.add("[ctp_overlay0]build.gradle[/]");
        projectTree.add("[ctp_overlay0]settings.gradle[/]");

        Clique.frame(BoxType.ROUNDED, "ctp_overlay0")
                .title("[ctp_overlay0]Module Structure[/]", FrameAlign.LEFT)
                .nest(projectTree, FrameAlign.LEFT)
                .render();

        System.out.println();

        // Compilation progress
        ProgressBarConfiguration compileConfig = ProgressBarConfiguration.builder()
                .styleRange(0, 40, "[ctp_red]:bar[/]  [ctp_subtext1]:percent%[/]  [ctp_overlay0]compiling...[/]")
                .styleRange(40, 75, "[ctp_yellow]:bar[/]  [ctp_subtext1]:percent%[/]  [ctp_overlay0]compiling...[/]")
                .styleRange(75, 100, "[ctp_green]:bar[/]  [ctp_subtext1]:percent%[/]  [ctp_overlay0]compiling...[/]")
                .complete('█')
                .incomplete('░')
                .length(45)
                .build();

        var compileBar = Clique.progressBar(9, compileConfig);
        for (int i = 0; i < 9; i++) {
            compileBar.tick();
            sleep(120);
        }

        System.out.println();

        // Warnings & errors list
        Clique.list()
                .item(
                        Clique.ink().yellow().on("⚠"),
                        "[ctp_yellow, bold]ConfigLoader.java:47[/]  [ctp_overlay0]—[/]  [ctp_subtext1]Deprecated API usage: StringUtils.format()[/]",
                        Clique.list()
                                .item("[ctp_overlay0]→[/]", "[ctp_overlay0]Use[/] [ctp_sky]StringUtils.formatAndReset()[/] [ctp_overlay0]instead[/]")
                )
                .item(
                        Clique.ink().red().on("✗"),
                        "[ctp_red, bold]PaymentController.java:112[/]  [ctp_overlay0]—[/]  [ctp_subtext1]Cannot resolve symbol 'StripeClient'[/]",
                        Clique.list()
                                .item("[ctp_overlay0]→[/]", "[ctp_overlay0]Missing dependency:[/] [ctp_red]stripe-java[/] [ctp_overlay0]not in build.gradle[/]")
                                .item("[ctp_overlay0]→[/]", "[ctp_overlay0]Add:[/] [ctp_sky]implementation 'com.stripe:stripe-java:24.3.0'[/]")
                )
                .render();

        System.out.println();
        Clique.parser().print(
                Clique.ink().yellow().on("⚠") + " [ctp_yellow]Compilation finished with errors[/]  " +
                        "[ctp_overlay0]8 files compiled  ·  1 error  ·  1 warning[/]"
        );
        System.out.println();
        sleep(300);

        // ─── STEP 3: TESTS ─────────────────────────────────────────────────────
        Clique.parser().print("[ctp_overlay0]━━━  [ctp_teal, bold]STEP 3[/][ctp_overlay0]  Running Tests  ━━━[/]");
        System.out.println();

        List<String> testFiles = List.of(
                "AuthServiceTest", "UserRepositoryTest", "SessionManagerTest",
                "StringUtilsTest", "DateUtilsTest", "UserControllerTest",
                "AuthControllerTest", "IntegrationTest"
        );

        ProgressBarConfiguration testConfig = ProgressBarConfiguration
                .fromPreset(ProgressBarPreset.BLOCKS)
                .format("[ctp_teal]:bar[/]  [ctp_subtext1]:percent%[/]  [ctp_overlay0][:elapsed][/]")
                .build();

        for (var file : Clique.progressBar(testFiles, testConfig)) {
            sleep(180);
        }

        System.out.println();
        sleep(200);

        // Test results table
        var testTable = Clique.table(TableType.ROUNDED_BOX_DRAW, "ctp_overlay0")
                .headers(
                        "[ctp_subtext1, bold]Test Suite[/]",
                        "[ctp_subtext1, bold]Tests[/]",
                        "[ctp_subtext1, bold]Passed[/]",
                        "[ctp_subtext1, bold]Failed[/]",
                        "[ctp_subtext1, bold]Skipped[/]",
                        "[ctp_subtext1, bold]Duration[/]"
                )
                .row("[ctp_text]AuthServiceTest[/]",       "12", "[ctp_green]12[/]", "[ctp_red]0[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.43s[/]")
                .row("[ctp_text]UserRepositoryTest[/]",    "9",  "[ctp_green]9[/]",  "[ctp_red]0[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.31s[/]")
                .row("[ctp_text]SessionManagerTest[/]",    "15", "[ctp_green]14[/]", "[ctp_red]1[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.67s[/]")
                .row("[ctp_text]StringUtilsTest[/]",       "20", "[ctp_green]20[/]", "[ctp_red]0[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.18s[/]")
                .row("[ctp_text]DateUtilsTest[/]",         "8",  "[ctp_green]8[/]",  "[ctp_red]0[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.12s[/]")
                .row("[ctp_text]UserControllerTest[/]",    "11", "[ctp_green]10[/]", "[ctp_red]1[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.54s[/]")
                .row("[ctp_text]AuthControllerTest[/]",    "7",  "[ctp_green]7[/]",  "[ctp_red]0[/]",  "[ctp_overlay0]0[/]", "[ctp_overlay0]0.29s[/]")
                .row("[ctp_text]IntegrationTest[/]",       "5",  "[ctp_green]3[/]",  "[ctp_red]0[/]",  "[ctp_yellow]2[/]",   "[ctp_overlay0]1.23s[/]");

        testTable.render();
        System.out.println();
        sleep(200);

        // ─── FINAL SUMMARY ─────────────────────────────────────────────────────
        Clique.frame(BoxType.DOUBLE_LINE, "ctp_red")
                .title("[ctp_red, bold]  BUILD FAILED  [/]", FrameAlign.CENTER)
                .nest("[ctp_subtext1]Duration:[/]  [ctp_text, bold]4.23s[/]    [ctp_subtext1]Finished:[/]  [ctp_text]" + java.time.LocalTime.now().withNano(0) + "[/]", FrameAlign.CENTER)
                .nest("")
                .nest(
                        Clique.table(TableType.COMPACT)
                                .headers("[ctp_subtext1]Stage[/]", "[ctp_subtext1]Status[/]", "[ctp_subtext1]Details[/]")
                                .row("[ctp_text]Dependencies[/]", "[ctp_green]✓  Passed[/]",  "[ctp_overlay0]5 resolved[/]")
                                .row("[ctp_text]Compilation[/]",  "[ctp_red]✗  Failed[/]",   "[ctp_overlay0]1 error  ·  1 warning[/]")
                                .row("[ctp_text]Tests[/]",        "[ctp_yellow]⚠  Partial[/]", "[ctp_overlay0]85/87 passed  ·  2 skipped[/]"),
                        FrameAlign.CENTER
                )
                .nest("")
                .nest("[ctp_subtext1]Fix [ctp_red]PaymentController.java:112[/][ctp_subtext1] to unblock the pipeline.[/]", FrameAlign.CENTER)
                .render();
    }

    private static void sleep(long ms) throws InterruptedException {
        Thread.sleep(ms);
    }
}