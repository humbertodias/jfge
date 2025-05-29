[![CI](https://github.com/humbertodias/jfge/actions/workflows/ci.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/ci.yml)
[![CI](https://github.com/humbertodias/jfge/actions/workflows/ci.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/ci.yml)
![GitHub all releases](https://img.shields.io/github/downloads/humbertodias/jfge/total)

## 🕹️ Java Fighting Game Engine

The **Java Fighting Game Engine** ([JFGE](https://code.google.com/archive/p/java-fighting-game-engine/downloads)) is a **modular, cross-platform framework** for building classic **2D sprite-based fighting games** using Java.

Originally developed as an open-source project, JFGE aims to simplify the creation of arcade-style fighting games by abstracting away low-level concerns and offering a **clean, extensible architecture** powered by **Google Guice** and the **Inversion of Control (IoC)** principle.

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

- Java 8+
- Maven 3.6+

You can manage SDKs using [SDKMAN!](https://sdkman.io):

Install
```bash
sdk install java 8.0.442-amzn
sdk install maven 3.6.3
```

Use (if already installed)
```bash
sdk use java 8.0.442-amzn
sdk use maven 3.6.3
```
### ⚙️ Building the Project

```bash
mvn package
```

This compiles the modules and produces runnable `.jar` files for each demo game.

### ▶️ Running a Game

Linux
```bash
./run.sh
```
Windows
```bash
.\run.ps1
```
OSX
```bash
./run.command
```

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

* [ ] Adding sound support
* [ ] Adding controller/gamepad support
* [ ] Adding networking support
* [ ] Porting to newer Java versions
* [ ] Improving the scripting DSL

Feel free to open an issue or fork the project!

### Documentation

- [CreatePersonalizedSprites](./doc/CreatePersonalizedSprites.md)