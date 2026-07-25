[![CI](https://github.com/humbertodias/jfge/actions/workflows/ci.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/ci.yml)
[![CD](https://github.com/humbertodias/jfge/actions/workflows/cd.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/cd.yml)
[![Deploy Web](https://github.com/humbertodias/jfge/actions/workflows/pages.yml/badge.svg)](https://github.com/humbertodias/jfge/actions/workflows/pages.yml)
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/humbertodias/jfge)
![GitHub all releases](https://img.shields.io/github/downloads/humbertodias/jfge/total)

## 🕹️ Java Fighting Game Engine

The **Java Fighting Game Engine** ([JFGE](https://code.google.com/archive/p/java-fighting-game-engine/downloads)) is a **modular, cross-platform framework** for building classic **2D sprite-based fighting games** using Java and [libGDX](https://libgdx.com/).

Fork of [JFGE](https://code.google.com/archive/p/java-fighting-game-engine/) from google code archive that aims to simplify the creation of arcade-style fighting games by abstracting away low-level concerns and offering a **clean, extensible architecture** powered by **Dagger 2** and the **Inversion of Control (IoC)** principle.

> Whether you're building a Street Fighter-style game or prototyping a new fighting mechanic, JFGE provides the tools to get started quickly.

### Key Features

* **Fighter Engine**: Built-in support for characters, projectiles, and visual effects
* **Multiple Arenas**: Swap between arenas and customize visuals
* **Lightweight Scripting Language**: Describe collision detection and AI behavior in a simple DSL
* **Modular Architecture**: Clean separation of game components for reusability and testing
* **Cross-Platform Support**: libGDX desktop, web (TeaVM), and Android (primary), plus legacy Java SE (Swing)
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
./gradlew build
```

This compiles the modules and produces runnable `.jar` files for each demo game.

### ▶️ Running a Game (libGDX desktop)

```bash
./gradlew :desktop:runMk2
./gradlew :desktop:runSf2
./gradlew :desktop:runSfVsMk2
```

### ▶️ Running a Game (libGDX web)

The web target uses [gdx-teavm](https://github.com/xpenatan/gdx-teavm) (TeaVM) to compile the game to JavaScript and run it in the browser.

**1. Download assets** (required for sprites and images):

```bash
make -f makefile-assets get_assets
```

**2. Start the dev server** and open http://localhost:8080 in your browser:

```bash
./gradlew :html:runMk2
./gradlew :html:runSf2
./gradlew :html:runSfVsMk2
```

**3. Production build** (static files for hosting):

```bash
./gradlew :html:gdx_teavm_web_js_build
```

Output directory: `html/build/dist/js/webapp/` (`index.html`, `app.js`, and bundled assets).

Serve that folder with any static HTTP server. Do not open `index.html` directly via `file://`.

**GitHub Pages:** pushes to `libgdx` or `main` build the browser targets and publish them to the `gh-pages` branch via [`.github/workflows/pages.yml`](.github/workflows/pages.yml). Enable **Settings → Pages → Build and deployment → Deploy from a branch → `gh-pages` / `/ (root)`**, then open https://humbertodias.github.io/jfge/

**Controls (keyboard):** same bindings as desktop — player 1 uses WASD and adjacent keys; player 2 uses arrow keys and the numpad. See `engine/config/org/jfge/config/controller/keyboard.properties`.

### ▶️ Running a Game (Swing legacy)

```bash
./gradlew :j2se:runMk2
./gradlew :j2se:runSf2
./gradlew :j2se:runSfVsMk2
```

Fat JAR scripts (legacy):

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



### 📂 Project Structure (libGDX)

```
.
├── engine/            # JFGE engine core (platform-independent)
├── ext/               # Engine extensions (physics, scenes)
├── core/              # libGDX platform layer (graphics, input)
├── desktop/           # LWJGL3 desktop launchers
├── html/              # TeaVM web launchers (browser)
├── android/           # libGDX Android launchers
├── j2se/              # Legacy Swing backend
├── mk2/               # Mortal Kombat II demo
├── sf2/               # Street Fighter II demo
├── sfvsmk2/           # Crossover game demo
└── mk2.zip|sf2.zip    # External sprite/image assets
```

### For local development

Download assets:

```shell
make -f makefile-assets get_assets
```

Then open the `desktop`, `html`, or `core` module in your IDE.

**Desktop (LWJGL3):**

```
./gradlew :desktop:runMk2
./gradlew :desktop:runSf2
./gradlew :desktop:runSfVsMk2
```

**Web (browser):**

```
./gradlew :html:runMk2
./gradlew :html:runSf2
./gradlew :html:runSfVsMk2
```

**Swing (legacy):**

```
./gradlew :j2se:runMk2
./gradlew :j2se:runSf2
./gradlew :j2se:runSfVsMk2
```

### Android (libGDX)

1. Install the [Android SDK](https://developer.android.com/studio) and create `android/local.properties`:

```properties
sdk.dir=/path/to/Android/Sdk
```

The `android` Gradle module is included automatically when `android/local.properties` exists or `ANDROID_HOME` is set.

2. Download game assets:

```shell
make -f makefile-assets get_assets
```

3. Build and install:

```bash
./gradlew :android:assembleDebug
./gradlew :android:installDebug
```

Launchers:
- `AndroidLauncher` — SF vs MK2 (default app icon)
- `Mk2AndroidLauncher` — Mortal Kombat 2
- `Sf2AndroidLauncher` — Street Fighter 2

Run a specific launcher from Android Studio or via `adb`:

```bash
adb shell am start -n org.jfge.android/.Mk2AndroidLauncher
```

### Joystick support (Swing)

```shell
./gradlew :j2se:copyJinputNatives
./gradlew :j2se:runMk2
```

### 🤝 Contributing

Contributions or forks are welcome to revive or modernize the engine.

If you're interested in:

* [x] Porting to newer Java versions - https://github.com/humbertodias/jfge/pull/1
* [x] libGDX desktop support
* [x] libGDX web support (TeaVM)
* [x] libGDX Android support
* [ ] Adding sound support - https://github.com/humbertodias/jfge/issues/4
* [ ] Improve player movement - https://github.com/humbertodias/jfge/issues/11
* [ ] Adding controller/gamepad support - https://github.com/humbertodias/jfge/issues/5
* [ ] Adding networking support
* [ ] Improving the scripting DSL

Feel free to open an [issue](https://github.com/humbertodias/jfge/issues) or fork the project!

### Documentation

- [Create Personalized Sprites](doc/create-personalized-sprites.md)
- [Development](./doc/dev.md)
- [jinput](https://jinput.github.io/jinput/)
