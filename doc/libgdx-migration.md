# libGDX Migration Guide

## Overview

This document describes the conversion of the Java Fighting Game Engine (JFGE) from Java2D/Swing to libGDX as the graphics and windowing backend.

## What Changed

### New Modules

#### org.jfge.libgdx
A new module that implements JFGE's SPI (Service Provider Interface) using libGDX primitives:

- **LibGdxGraphicsProvider**: Manages the libGDX SpriteBatch and ShapeRenderer
- **LibGdxGraphics**: Implements JFGE's Graphics interface using libGDX rendering
- **LibGdxGraphicsFactory**: Creates libGDX-backed graphics objects (Color, Font, Image, Rectangle)
- **LibGdxColor**: Color implementation using libGDX's Color class
- **LibGdxFont**: Font implementation using libGDX's BitmapFont
- **LibGdxImage**: Image implementation using libGDX's Texture and TextureRegion
- **LibGdxRectangle**: Rectangle implementation using libGDX's Rectangle
- **LibGdxKeyboardController1/2**: Input adapters for player 1 and player 2
- **LibGdxGraphicsModule**: Guice module for dependency injection

#### org.jfge.desktop
Desktop launcher module for running games with libGDX:

- **JfgeApplicationAdapter**: Bridges libGDX's ApplicationAdapter with JFGE's Game interface
- **DesktopLauncherSF2**: Main entry point for Street Fighter 2
- **DesktopLauncherMK2**: Main entry point for Mortal Kombat 2
- **DesktopLauncherSfVsMk2**: Main entry point for Street Fighter vs Mortal Kombat 2

### Architecture

The conversion maintains JFGE's clean architecture by:

1. **Preserving the SPI layer**: All game logic remains unchanged
2. **Adding a new implementation**: libGDX implements the same SPI interfaces as Java2D
3. **Keeping backward compatibility**: The old Java2D implementation still works
4. **Using dependency injection**: Guice swaps implementations at runtime

```
┌─────────────────────────────────────────┐
│         Game Modules (SF2, MK2)         │
│     (No changes - uses SPI only)        │
└─────────────────────────────────────────┘
                    │
                    ↓
┌─────────────────────────────────────────┐
│            Core Engine (SPI)             │
│  Graphics, GraphicsProvider, Image, etc. │
└─────────────────────────────────────────┘
                    │
          ┌─────────┴─────────┐
          ↓                   ↓
┌──────────────────┐  ┌──────────────────┐
│  org.jfge.j2se   │  │  org.jfge.libgdx │
│   (Java2D impl)  │  │   (libGDX impl)  │
└──────────────────┘  └──────────────────┘
```

## Benefits

### Performance
- **Hardware acceleration**: libGDX uses OpenGL for rendering
- **Optimized sprite batching**: Better performance for many sprites
- **Efficient resource management**: Texture atlases and pooling

### Cross-Platform
- **Desktop**: Windows, Linux, macOS (via LWJGL3)
- **Mobile**: Android, iOS (future support)
- **Web**: HTML5/WebGL (future support)

### Modern
- **Active development**: libGDX is actively maintained
- **Large community**: Extensive documentation and examples
- **Mature ecosystem**: Many tools and libraries

### Developer Experience
- **Better debugging**: libGDX has excellent debugging tools
- **Asset pipeline**: Built-in tools for texture packing, font generation
- **Scene2D UI**: Optional UI framework for menus and HUD

## How to Use

### Running with libGDX (Recommended)

```bash
# Street Fighter 2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherSF2

# Mortal Kombat 2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherMK2

# Street Fighter vs Mortal Kombat 2
gradle :desktop:run -PmainClass=org.jfge.desktop.DesktopLauncherSfVsMk2
```

### Running with Java2D (Legacy)

The old scripts still work:
```bash
./run-linux.sh          # Linux
./run-macos.command     # macOS
.\run-windows.ps1       # Windows
```

Or via Gradle:
```bash
gradle :sf2:run
gradle :mk2:run
gradle :sfvsmk2:run
```

## Technical Details

### Threading Model

**Critical**: The libGDX integration uses a different threading model than the original JFGE design:

- **Original JFGE**: Creates a separate thread for the game loop in `EngineImpl.start()`
- **libGDX Integration**: Uses libGDX's render thread for all game updates and rendering

The `JfgeApplicationAdapter` calls `game.startState()` instead of `game.start()` to initialize the game without starting the engine's thread. The game loop is then driven by libGDX's `render()` callback, which:
1. Updates game state via `game.update()`
2. Renders the game via `game.render()`
3. All OpenGL calls happen on the correct thread

This change is necessary because OpenGL contexts are thread-specific - all OpenGL calls must happen on the thread that created the context (libGDX's render thread).

### Graphics Coordinate System

**Important**: libGDX uses a different coordinate system than Java2D:
- **Java2D**: Origin (0,0) is top-left, Y increases downward
- **libGDX**: Origin (0,0) is bottom-left, Y increases upward

The LibGdxGraphics implementation handles this transparently by not transforming coordinates, as JFGE's game logic expects top-left origin. The games work correctly because libGDX's projection matrix is set up to match Java2D's coordinate system.

### Color Format

Colors use ARGB format (0xAARRGGBB):
- Alpha: bits 24-31
- Red: bits 16-23
- Green: bits 8-15
- Blue: bits 0-7

LibGdxColor correctly extracts these components and converts them to libGDX's 0.0-1.0 float range.

### Image Handling

- **Loading**: Images are loaded from the classpath as libGDX Textures
- **Flipping**: Horizontal flipping uses TextureRegion
- **Rotation**: Not yet implemented (not used by current games)

### Input Handling

Keyboard input is mapped through libGDX's InputAdapter:
- Player 1: Arrow keys, ZXCVASDF
- Player 2: WASD, UIOJKLGH

The controllers use the same event names as Java2D, so game logic is unchanged.

### Resource Management

**Important**: libGDX resources must be disposed:
- Textures
- Fonts
- SpriteBatch
- ShapeRenderer

The LibGdxGraphicsProvider handles cleanup in its dispose() method, which is called when the application exits.

## Known Limitations

1. **Image rotation**: The rotate() method is not implemented, but this feature is not used by the games
2. **Font rendering**: Uses libGDX's default BitmapFont instead of TrueType fonts
3. **Assets required**: Games need sprite assets to run (download with `make -f makefile-assets get_assets`)

## Future Enhancements

### Short-term
- [ ] Implement proper image rotation using Pixmap
- [ ] Add TrueType font support via FreeType
- [ ] Add gamepad/controller support using libGDX's Controllers extension

### Long-term
- [ ] Port to Android
- [ ] Port to iOS (via RoboVM)
- [ ] Port to HTML5 (via GWT)
- [ ] Add particle effects for special moves
- [ ] Add shader-based visual effects

## Migration for Custom Games

If you're building a custom game on JFGE, you can switch to libGDX by:

1. **Create a desktop launcher** (similar to DesktopLauncherSF2)
2. **Use LibGdxGraphicsModule** instead of J2SeGraphicsModule
3. **Test thoroughly** - while the SPI is the same, there may be subtle differences
4. **Consider mobile** - you can now target Android and iOS

Example launcher:
```java
public class MyGameLauncher {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(
        new EngineModule(),
        new LibGdxGraphicsModule(),  // Use libGDX
        // ... other modules
        new MyGameModule()
    );

    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("My Fighting Game");
    config.setWindowedMode(640, 480);
    config.setForegroundFPS(60);

    new Lwjgl3Application(
        new JfgeApplicationAdapter(injector, "myGame"), 
        config
    );
  }
}
```

## Troubleshooting

### "Could not initialize class com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics"
- Make sure you have OpenGL drivers installed
- Try updating your graphics drivers

### "File not found" when loading images
- Check that assets are in the correct location
- Run `make -f makefile-assets get_assets` to download sprites
- Verify paths in your game modules

### Black screen
- Check console for errors
- Verify that assets loaded correctly
- Make sure LibGdxGraphicsProvider.create() is called

## References

- [libGDX Documentation](https://libgdx.com/wiki/)
- [libGDX GitHub](https://github.com/libgdx/libgdx)
- [JFGE Original Project](https://code.google.com/archive/p/java-fighting-game-engine/)
