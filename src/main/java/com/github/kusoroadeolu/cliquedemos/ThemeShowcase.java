package com.github.kusoroadeolu.cliquedemos;

import io.github.kusoroadeolu.clique.Clique;
import io.github.kusoroadeolu.clique.spi.CliqueTheme;

import java.util.List;

public class ThemeShowcase {

    public static void main(String[] args) {
        Clique.parser().print("[bold]🎨 [ul]Clique Theme Showcase[/]\n");

        List<CliqueTheme> themes = Clique.discoverThemes();

        for (CliqueTheme theme : themes) {
            showcaseTheme(theme);
        }

        Clique.parser().print("\n[bold, green]✓ Showcase complete![/]");
    }

    private static void showcaseTheme(CliqueTheme theme) {
        String themeName = theme.themeName();
        Clique.registerTheme(themeName);

        Clique.parser().print("[bold, ul]" + formatThemeName(themeName) + "[/]");

        // Show theme-specific colors based on theme type
        if (themeName.startsWith("catppuccin")) {
            showcaseCatppuccin(themeName);
        } else if (themeName.equals("dracula")) {
            showcaseDracula();
        } else if (themeName.startsWith("gruvbox")) {
            showcaseGruvbox(themeName);
        } else if (themeName.equals("nord")) {
            showcaseNord();
        } else if (themeName.equals("tokyo-night")) {
            showcaseTokyoNight();
        }
    }

    private static void showcaseCatppuccin(String variant) {
        Clique.parser().print("[ctp_mauve]● Mauve[/]  ");
        Clique.parser().print("[ctp_pink]● Pink[/]  ");
        Clique.parser().print("[ctp_red]● Red[/]  ");
        Clique.parser().print("[ctp_peach]● Peach[/]  ");
        Clique.parser().print("[ctp_yellow]● Yellow[/]  ");
        Clique.parser().print("[ctp_green]● Green[/]  ");
        Clique.parser().print("[ctp_blue]● Blue[/]");

        Clique.parser().print("[bg_ctp_surface0, ctp_text] Example UI Box [/] ");
        Clique.parser().print("[ctp_green]✓ Success[/] ");
        Clique.parser().print("[ctp_red]✗ Error[/] ");
        Clique.parser().print("[ctp_yellow]⚠ Warning[/]\n");
    }

    private static void showcaseDracula() {
        Clique.parser().print("[drac_magenta]● Magenta[/]  ");
        Clique.parser().print("[drac_cyan]● Cyan[/]  ");
        Clique.parser().print("[drac_green]● Green[/]  ");
        Clique.parser().print("[drac_yellow]● Yellow[/]  ");
        Clique.parser().print("[drac_red]● Red[/]  ");
        Clique.parser().print("[*drac_white]● Bright White[/]");

        Clique.parser().print("[drac_magenta]♦[/] [*drac_white, bold]Dracula Theme[/] ");
        Clique.parser().print("[drac_green]✓[/] Success ");
        Clique.parser().print("[drac_red]✗[/] Error\n");
    }

    private static void showcaseGruvbox(String variant) {
        Clique.parser().print("[gb_red]● Red[/]  ");
        Clique.parser().print("[gb_orange]● Orange[/]  ");
        Clique.parser().print("[gb_yellow]● Yellow[/]  ");
        Clique.parser().print("[gb_green]● Green[/]  ");
        Clique.parser().print("[gb_aqua]● Aqua[/]  ");
        Clique.parser().print("[gb_blue]● Blue[/]  ");
        Clique.parser().print("[gb_purple]● Purple[/]");

        Clique.parser().print("[gb_orange, bold]⚡ Gruvbox[/] ");
        Clique.parser().print("[bg_gb_bg1, gruvbox_aqua] Info [/] ");
        Clique.parser().print("[gb_fg]Retro vibes[/]\n");
    }

    private static void showcaseNord() {
        Clique.parser().print("[nord_frost0]● Frost 0[/]  ");
        Clique.parser().print("[nord_frost1]● Frost 1[/]  ");
        Clique.parser().print("[nord_frost2]● Frost 2[/]  ");
        Clique.parser().print("[nord_frost3]● Frost 3[/]");

        Clique.parser().print("[nord_frost2]❄[/] [nord_snow, bold]Nord Theme[/] ");
        Clique.parser().print("[nord_green]✓ Success[/] ");
        Clique.parser().print("[nord_red]✗ Error[/] ");
        Clique.parser().print("[nord_yellow]⚠ Warning[/]\n");
    }

    private static void showcaseTokyoNight() {
        Clique.parser().print("[tokyo_magenta]● Magenta[/]  ");
        Clique.parser().print("[tokyo_cyan]● Cyan[/]  ");
        Clique.parser().print("[tokyo_blue]● Blue[/]  ");
        Clique.parser().print("[tokyo_green]● Green[/]  ");
        Clique.parser().print("[tokyo_yellow]● Yellow[/]  ");
        Clique.parser().print("[tokyo_red]● Red[/]");

        Clique.parser().print("[tokyo_magenta]◆[/] [*tokyo_white, bold]Tokyo Night[/] ");
        Clique.parser().print("[tokyo_cyan]›[/] [tokyo_fg]Modern & clean[/]");
    }

    private static String formatThemeName(String name) {
        return name.substring(0, 1).toUpperCase() +
                name.substring(1).replace("-", " ");
    }
}