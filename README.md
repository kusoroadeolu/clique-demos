# Clique Demos

A collection of interactive demos showcasing the capabilities of the [Clique](https://github.com/kusoroadeolu/Clique) library for building beautiful CLI applications in Java.

## What's Inside

### CLI Art Gallery
An interactive tour through Clique's visual capabilities, featuring:
- Rainbow typography and gradients
- Styled quotes and banners
- Complete color palette showcase
- Colored ASCII art
- Geometric patterns
- Theme gallery with popular color schemes

**Run it:**
```bash
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.CliArtGallery"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.CliArtGallery
```

### Theme Showcase
See all available themes in action with live examples of each color palette.

**Run it:**
```bash
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.ThemeShowcase"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.ThemeShowcase
```

### Semantic Colors Demo
Learn the pattern of defining semantic color aliases (error, success, warning, info) for consistent styling across your application. Features:
- Application log viewer
- Deployment status display
- System health monitoring

**Run it:**
```bash
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.SemanticColorsDemo"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.SemanticColorsDemo
```

### Quiz Game
A colorful Java knowledge quiz with:
- Styled tables for questions
- Real-time scoring
- Color-coded feedback

**Run it:**
```bash
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.QuizGame"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.QuizGame
```

### Code Scanner
Analyze your Java projects with styled output showing:
- File statistics
- Code complexity metrics
- TODO comments finder

**Run it:**
```bash
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.CodeScanner" -Dexec.args="/path/to/your/project"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.CodeScanner /path/to/your/project
```

### Project Explorer
Browse project structure and file statistics with beautiful tables.

**Run it:**
```bash
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.ProjectExplorer" -Dexec.args="/path/to/your/project"
# or
java -cp target/clique-demos.jar com.github.kusoroadeolu.cliquedemos.ProjectExplorer /path/to/your/project
```

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
    <version>v0.0.2</version>
</dependency>
```

**Using Gradle**
```gradle
// Add it in your root settings.gradle at the end of repositories:

    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            mavenCentral()
            maven { url 'https://jitpack.io' }
        }
    }

    dependencies {
        implementation 'com.github.kusoroadeolu:clique-demos:v0.0.2'
    }
```

```bash
# Clone the repo
git clone https://github.com/kusoroadeolu/clique-demos.git
cd clique-demos

# Build the project
mvn clean package

# Run any demo
mvn exec:java -Dexec.mainClass="com.github.kusoroadeolu.cliquedemos.CliArtGallery"
```

## Learn More

These demos showcase different aspects of Clique:
- **Markup parsing** - Simple `[color]text[/]` syntax
- **StyleBuilder** - Fluent API for dynamic styling
- **Tables** - Beautiful ASCII tables with custom borders
- **Themes** - Pre-built color schemes (Catppuccin, Dracula, Nord, etc.)
- **Custom colors** - RGB true color support
- **Composability** - Combine styles for semantic naming

Check out the [full documentation](https://github.com/kusoroadeolu/Clique) to learn how to use Clique in your own projects.

## Best Demos for Learning

- **New to Clique?** Start with `CliArtGallery` to see what's possible
- **Want to use themes?** Check out `ThemeShowcase` and `SemanticColorsDemo`
- **Building a CLI app?** Look at `QuizGame` for interactive examples
- **Need tables?** `ProjectExplorer` shows table formatting


## License

MIT License - see [LICENSE](LICENSE) file for details.

## Links

- [Clique Library](https://github.com/kusoroadeolu/Clique)
- [Documentation](https://github.com/kusoroadeolu/Clique/docs)
- [Report Issues](https://github.com/kusoroadeolu/clique-demos/issues)