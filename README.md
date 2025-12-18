[![CI](https://github.com/humbertodias/jfge/actions/workflows/ci.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/ci.yml)
[![CD](https://github.com/humbertodias/jfge/actions/workflows/cd.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/cd.yml)
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/humbertodias/jfge)
![GitHub all releases](https://img.shields.io/github/downloads/humbertodias/jfge/total)

## 🕹️ Java Fighting Game Engine

The **Java Fighting Game Engine** ([JFGE](https://code.google.com/archive/p/java-fighting-game-engine/downloads)) is a **modular, cross-platform framework** for building classic **2D sprite-based fighting games** using Java.

Fork of [JFGE](https://code.google.com/archive/p/java-fighting-game-engine/) from google code archive that aims to simplify the creation of arcade-style fighting games by abstracting away low-level concerns and offering a **clean, extensible architecture** powered by **Google Guice** and the **Inversion of Control (IoC)** principle.

**Now powered by libGDX** for enhanced cross-platform support and modern graphics capabilities!

> Whether you're building a Street Fighter-style game or prototyping a new fighting mechanic, JFGE provides the tools to get started quickly.

### Key Features

* **Fighter Engine**: Built-in support for characters, projectiles, and visual effects
* **Multiple Arenas**: Swap between arenas and customize visuals
* **Lightweight Scripting Language**: Describe collision detection and AI behavior in a simple DSL
* **Modular Architecture**: Clean separation of game components for reusability and testing
* **Cross-Platform Support**: Powered by libGDX - runs on Desktop (Windows, Linux, macOS), with potential for Android, iOS, and Web
* **Graphics Abstraction**: Unified API for rendering and input across platforms
* **Extensibility**: Add new fighters, moves, or mechanics via pluggable modules

---

### 📦 Requirements

- Java 17+

You can manage SDKs using [SDKMAN!](https://sdkman.io):

Install
```bash
sdk install java 17.0.15-amzn
sdk install gradle 8.14.1
```

Use (if already installed)
```bash
sdk use java 17.0.15-amzn
sdk use gradle 8.14.1
```
### ⚙️ Building the Project

```bash
gradle build
```

This compiles the modules and produces runnable `.jar` files for each demo game.

### ▶️ Running a Game

#### Using libGDX (Recommended)

Run games with libGDX backend (modern, cross-platform):

```bash
# Street Fighter 2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherSF2

# Mortal Kombat 2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherMK2

# Street Fighter vs Mortal Kombat 2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherSfVsMk2
```

#### Using Java2D (Legacy)

Run games with the legacy Java Swing backend:

```bash
./run-linux.sh
```
or
```bash
.\run-windows.ps1
```
or
```bash
./run-macos.command
```


https://github.com/user-attachments/assets/42821ca2-e2fe-44c7-9d77-eb5aef6c2a48



### 📂 Project Structure
```
.
├── org.jfge.core/            # Engine core and reusable modules
├── org.jfge.libgdx/          # libGDX graphics implementation
├── org.jfge.desktop/         # Desktop launcher for libGDX
├── org.jfge.j2se/            # Legacy Java2D/Swing implementation
├── org.jfge.games.sf2/       # Street Fighter II demo
├── org.jfge.games.mk2/       # Mortal Kombat II demo
├── org.jfge.games.sfvsmk2/   # Crossover game demo
└── sf2.zip|mk2.zip|etc       # External sprite/image assets
```

### For local development

Download assets
```shell
make -f makefile-assets get_assets
```
Then open the module [mk2,sf2,sfvsmk2] in your IDE 

#### Run with libGDX (Recommended)
```
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherSF2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherMK2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherSfVsMk2
```

#### Run with Java2D (Legacy)
```
gradle :mk2:run
gradle :sf2:run
gradle :sfvsmk2:run
```

### Joystick support (Java2D only)

```shell
gradle :j2se:copyJinputNatives
gradle :mk2:run
```

### 🤝 Contributing

Contributions or forks are welcome to revive or modernize the engine.

If you're interested in:

* [x] Porting to newer Java versions - https://github.com/humbertodias/jfge/pull/1
* [ ] Adding sound support - https://github.com/humbertodias/jfge/issues/4
* [ ] Improve player movement - https://github.com/humbertodias/jfge/issues/11
* [ ] Adding controller/gamepad support - https://github.com/humbertodias/jfge/issues/5
* [ ] Android support - https://github.com/humbertodias/jfge/issues/2
* [ ] Adding networking support
* [ ] Improving the scripting DSL

Feel free to open an [issue](https://github.com/humbertodias/jfge/issues) or fork the project!

### Documentation

- [Create Personalized Sprites](doc/create-personalized-sprites.md)
- [Development](./doc/dev.md)
- [jinput](https://jinput.github.io/jinput/)
