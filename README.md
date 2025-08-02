[![CI](https://github.com/humbertodias/jfge/actions/workflows/ci.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/ci.yml)
[![CD](https://github.com/humbertodias/jfge/actions/workflows/cd.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/cd.yml)
![GitHub all releases](https://img.shields.io/github/downloads/humbertodias/jfge/total)

## 🕹️ Java Fighting Game Engine

The **Java Fighting Game Engine** ([JFGE](https://code.google.com/archive/p/java-fighting-game-engine/downloads)) is a **modular, cross-platform framework** for building classic **2D sprite-based fighting games** using Java.

Fork of [JFGE](https://code.google.com/archive/p/java-fighting-game-engine/) from google code archive that aims to simplify the creation of arcade-style fighting games by abstracting away low-level concerns and offering a **clean, extensible architecture** powered by **Google Guice** and the **Inversion of Control (IoC)** principle.

> Whether you're building a Street Fighter-style game or prototyping a new fighting mechanic, JFGE provides the tools to get started quickly.

### Key Features

* **Fighter Engine**: Built-in support for characters, projectiles, and visual effects
* **Multiple Arenas**: Swap between arenas and customize visuals
* **Lightweight Scripting Language**: Describe collision detection and AI behavior in a simple DSL
* **Modular Architecture**: Clean separation of game components for reusability and testing
* **Cross-Platform Support**: Compatible with both Java SE and Android
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

<img width="1091" height="403" alt="image" src="https://github.com/user-attachments/assets/3f9d4cf4-b275-48e6-92dc-414284fd0f05" />


### 📂 Project Structure
```
.
├── org.jfge.core/            # Engine core and reusable modules
├── org.jfge.games.sf2/       # Street Fighter II demo
├── org.jfge.games.mk2/       # Mortal Kombat II demo
├── org.jfge.games.sfvsmk2/   # Crossover game demo
└── sf2.zip|mk2.zip|etc       # External sprite/image assets
```

### 🤝 Contributing

Contributions or forks are welcome to revive or modernize the engine.

If you're interested in:

* [x] Porting to newer Java versions - https://github.com/humbertodias/jfge/pull/1
* [ ] Adding sound support - https://github.com/humbertodias/jfge/issues/4
* [ ] Adding controller/gamepad support - https://github.com/humbertodias/jfge/issues/5
* [ ] Android support - https://github.com/humbertodias/jfge/issues/2
* [ ] Adding networking support
* [ ] Improving the scripting DSL

Feel free to open an [issue](https://github.com/humbertodias/jfge/issues) or fork the project!

### Documentation

- [Create Personalized Sprites](doc/create-personalized-sprites.md)
- [Development](./doc/dev.md)
