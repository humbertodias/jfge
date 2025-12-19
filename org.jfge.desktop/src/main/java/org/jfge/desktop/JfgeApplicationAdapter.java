package org.jfge.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.util.Map;
import org.jfge.api.game.Game;
import org.jfge.libgdx.controller.LibGdxKeyboardController1;
import org.jfge.libgdx.controller.LibGdxKeyboardController2;
import org.jfge.libgdx.graphics.LibGdxGraphics;
import org.jfge.libgdx.graphics.LibGdxGraphicsProvider;

public class JfgeApplicationAdapter extends ApplicationAdapter {

  private final Injector injector;
  private final String gameName;
  private Game game;
  private LibGdxGraphicsProvider graphicsProvider;
  private LibGdxKeyboardController1 controller1;
  private LibGdxKeyboardController2 controller2;

  public JfgeApplicationAdapter(Injector injector, String gameName) {
    this.injector = injector;
    this.gameName = gameName;
  }

  @Override
  public void create() {
    // Initialize graphics provider
    graphicsProvider = (LibGdxGraphicsProvider) injector.getInstance(
        org.jfge.spi.graphics.GraphicsProvider.class);
    graphicsProvider.create();

    // Set up input processors
    controller1 = (LibGdxKeyboardController1) injector.getInstance(
        Key.get(org.jfge.spi.controller.Controller.class,
            com.google.inject.name.Names.named("keyboard.controller1")));
    controller2 = (LibGdxKeyboardController2) injector.getInstance(
        Key.get(org.jfge.spi.controller.Controller.class,
            com.google.inject.name.Names.named("keyboard.controller2")));

    InputMultiplexer multiplexer = new InputMultiplexer();
    multiplexer.addProcessor(controller1);
    multiplexer.addProcessor(controller2);
    Gdx.input.setInputProcessor(multiplexer);

    // Get the game and initialize it (but don't start the engine thread)
    Map<String, Game> games =
        injector.getInstance(Key.get(new TypeLiteral<Map<String, Game>>() {}));
    game = games.get(gameName);
    if (game != null) {
      // Initialize game state without starting the engine's thread
      // The engine's thread-based loop is incompatible with libGDX's render loop
      game.startState();
    }
  }

  @Override
  public void render() {
    // Clear the screen
    if (graphicsProvider != null) {
      graphicsProvider.draw(); // Clears screen and ensures no batch is open
    }

    // Update game state
    if (game != null) {
      game.update();
    }

    // Render game
    if (graphicsProvider != null && game != null) {
      game.render(graphicsProvider.getGraphics());
      // End any open batch after rendering
      if (graphicsProvider.getGraphics() instanceof LibGdxGraphics) {
        ((LibGdxGraphics) graphicsProvider.getGraphics()).endBatch();
      }
    }
  }

  @Override
  public void dispose() {
    // Stop the game
    if (game != null) {
      game.end();
    }

    // Dispose graphics provider
    if (graphicsProvider != null) {
      graphicsProvider.dispose();
    }
  }

  @Override
  public void resize(int width, int height) {
    // Handle window resize if needed
  }

  @Override
  public void pause() {
    // Handle pause if needed
  }

  @Override
  public void resume() {
    // Handle resume if needed
  }
}
