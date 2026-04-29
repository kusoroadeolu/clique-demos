# Clique Demos

A collection of interactive demos showcasing the capabilities of the [Clique](https://github.com/kusoroadeolu/Clique) library for building beautiful CLI applications in Java.

## Quick Start

### Prerequisites
- Java 21 or higher
- Maven
- A terminal with truecolor (24-bit color) support

### Setup

**Using Maven**
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
<groupId>com.github.kusoroadeolu</groupId>
<artifactId>clique-demos</artifactId>
<version>v0.0.6</version>
</dependency>
```

**Using Gradle**
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.kusoroadeolu:clique-demos:v0.0.5'
}
```

```bash
# Clone the repo
git clone https://github.com/kusoroadeolu/clique-demos.git
cd clique-demos

# Build the project
mvn clean package
```

**Run it with the DemoLauncher**

For easier access, you can use the `DemoLauncher` class which provides simple methods to run any demo:
```java
DemoLauncher.runQuizGame();
DemoLauncher.runCliArtGallery();
DemoLauncher.runThemeShowcase();
DemoLauncher.runBookReviewDemo();
DemoLauncher.runBuildPipeline();
DemoLauncher.runBootScreen();
DemoLauncher.runLiveDashboard();
DemoLauncher.runThoughtStream();
DemoLauncher.runMusicVisualizer();

// Demos that need arguments
DemoLauncher.runCodeScanner("/path/to/your/project");
DemoLauncher.runProjectExplorer("/path/to/your/project");
```

---

## Demos

### Theme Showcase
See all available themes in action with live examples of each color palette.

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.ThemeShowcase"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.ThemeShowcase
```

### Semantic Colors Demo
Learn the pattern of defining semantic color aliases (error, success, warning, info) for consistent styling across your application. Features:
- Application log viewer
- Deployment status display
- System health monitoring

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.SemanticColorsDemo"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.SemanticColorsDemo
```

### Quiz Game
A colorful Java knowledge quiz with:
- Styled tables for questions
- Real-time scoring
- Color-coded feedback

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.QuizGame"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.QuizGame
```

### CLI Art Gallery
A gallery of ASCII art pieces rendered with Clique's markup and styling system.

```bash
mvn compile exec:java "-Dexec.mainClass=com.github.kusoroadeolu.cliquedemos.CliArtGallery"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.CliArtGallery
```

### Book Review Card
A styled book review card showcasing nested components. Features:
- Star ratings
- Metadata tables
- Quoted reader reviews in a styled box
- A "similar reads" tree with recommendations

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.BookReviewDemo"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.BookReviewDemo
```

### Boot Screen
An animated cyberpunk-style boot sequence. Features:
- BIOS-style hardware scan with a typewriter effect
- Memory test counter
- Animated progress bars for each boot stage
- Warning and system log lines
- ASCII "ACCESS GRANTED" finale

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.BootScreen"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.BootScreen
```

### Build Pipeline
A simulated CI/CD pipeline run with animated output. Features:
- Dependency resolution with a progress bar and item list
- Compilation results in a module tree with warnings and errors
- Test suite progress bar and results table
- A final build summary frame

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.BuildPipeline"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.BuildPipeline
```

### Live Dashboard
A live-updating terminal system monitor. Features:
- Service status table with simulated degradation and recovery events
- Sparkline charts for CPU, memory, and requests per second
- Refreshes every 500ms for 60 ticks

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.LiveDashboard"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.LiveDashboard
```

### Music Visualizer
A real-time terminal equalizer with 16 animated frequency bands. Features:
- Simulated bass, mid, and high frequency envelopes
- Fast attack and slow decay for a realistic feel
- Tokyo Night color scheme
- Runs for 30 seconds then fades out

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.MusicVisualizer"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.MusicVisualizer
```

### ThoughtStream
A real-time reasoning visualizer. Simulates an LLM thinking through a decision problem, building a live tree of thought branches color-coded by confidence - green for certain, yellow for uncertain, and red for contradictions or dead ends.

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.ThoughtStream"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.ThoughtStream
```

### Code Scanner
Analyze your Java projects with styled output showing:
- File statistics
- Code complexity metrics
- TODO/FIXME comment finder

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.CodeScanner" "-Dexec.args=/path/to/your/project"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.CodeScanner /path/to/your/project
```

### Project Explorer
Browse project structure and file statistics with beautiful tables.

```bash
mvn compile exec:java "-Dexec.mainClass=io.github.kusoroadeolu.clique.demos.ProjectExplorer" "-Dexec.args=/path/to/your/project"
# or
java -cp target/clique-demos.jar io.github.kusoroadeolu.clique.demos.ProjectExplorer /path/to/your/project
```

---

## Learn More

These demos showcase different aspects of Clique:
- **Markup parsing** - Simple `[color]text[/]` syntax
- **StyleBuilder and Ink** - Fluent APIs for dynamic styling
- **Tables** - Beautiful ASCII tables with custom borders
- **Themes** - Pre-built color schemes (Catppuccin, Dracula, Nord, Tokyo Night, etc.)
- **Progress bars** - Animated bars with style ranges and presets
- **Trees and Lists** - Fluent APIs for hierarchical data with colored connectors
- **Frames & Boxes** - Nestable layout containers
- **Custom colors and gradients** - RGB true color support

Check out the [full documentation](https://github.com/kusoroadeolu/Clique) to learn how to use Clique in your own projects.

## Links

- [Clique Library](https://github.com/kusoroadeolu/Clique)
- [Documentation](https://github.com/kusoroadeolu/Clique/docs)
- [Report Issues](https://github.com/kusoroadeolu/clique-demos/issues)