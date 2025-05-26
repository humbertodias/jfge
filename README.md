![GitHub all releases](https://img.shields.io/github/downloads/humbertodias/jfge/total)

## 🕹️ Java Fighting Game Engine (JFGE)

The **Java Fighting Game Engine** ([JFGE](https://code.google.com/archive/p/java-fighting-game-engine/downloads)) is a **modular, cross-platform framework** for building classic **2D sprite-based fighting games** using Java.

Originally developed as an open-source project, JFGE aims to simplify the creation of arcade-style fighting games by abstracting away low-level concerns and offering a **clean, extensible architecture** powered by **Google Guice** and the **Inversion of Control (IoC)** principle.

> 🎮 Whether you're building a Street Fighter-style game or prototyping a new fighting mechanic, JFGE provides the tools to get started quickly.

### ✨ Key Features

* ✅ **Fighter Engine**: Built-in support for characters, projectiles, and visual effects
* 🌍 **Multiple Arenas**: Easily swap between arenas and customize visuals
* 🧠 **Lightweight Scripting Language**: Describe collision detection and AI behavior in a simple DSL
* 🧩 **Modular Architecture**: Clean separation of game components for reusability and testing
* 📱 **Cross-Platform Support**: Compatible with both Java SE and Android
* 🎨 **Graphics Abstraction**: Unified API for rendering and input across platforms
* 🔌 **Extensibility**: Add new fighters, moves, or mechanics via pluggable modules

### 📦 Requirements

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

### 📁 Asset Management

The engine uses external sprites and animation assets, which need to be downloaded before running the games:

```bash
./assets.sh
```

### ⚙️ Building the Project

Run the following from the root of the repository:

```bash
mvn clean package
```

This compiles the modules and produces runnable `.jar` files for each demo game.

### ▶️ Running a Game

To launch a game module, use:

```bash
# Choose the game to run by setting the JAR path:

# Street Fighter II demo
# JAR=org.jfge.games.sf2/target/sf2-0.0.1-SNAPSHOT.jar

# Mortal Kombat II demo
# JAR=org.jfge.games.mk2/target/mk2-0.0.1-SNAPSHOT.jar

# Street Fighter vs Mortal Kombat crossover demo
JAR=org.jfge.games.sfvsmk2/target/sfvsmk2-0.0.1-SNAPSHOT.jar

# Run the selected game
java -jar $JAR
```

### 📂 Project Structure
```
.
├── common/                   # Engine core and reusable modules
├── org.jfge.games.sf2/       # Street Fighter II demo
├── org.jfge.games.mk2/       # Mortal Kombat II demo
├── org.jfge.games.sfvsmk2/   # Crossover game demo
└── assets/                   # External sprite/image assets
```

### 🤝 Contributing

Contributions or forks are welcome to revive or modernize the engine.

If you're interested in:

* Adding sound support
* Adding controller/gamepad support
* Adding networking support
* Porting to newer Java versions
* Improving the scripting DSL

Feel free to open an issue or fork the project!

### 📜 License

This project was originally distributed under the [Apache 2.0 License](LICENSE), and may be reused or modified accordingly.
