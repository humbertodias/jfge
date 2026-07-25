package org.jfge.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import org.jfge.api.engine.Engine;
import org.jfge.api.game.Game;
import org.jfge.libgdx.controller.LibGdxKeyboardController1;
import org.jfge.libgdx.controller.LibGdxKeyboardController2;
import org.jfge.libgdx.graphics.LibGdxGraphicsProvider;

public final class JfgeApplication extends ApplicationAdapter {

  private final JfgeLibGdxComponent component;
  private final String gameKey;

  private LibGdxGraphicsProvider graphicsProvider;
  private LibGdxKeyboardController1 controller1;
  private LibGdxKeyboardController2 controller2;
  private Engine engine;
  private Game game;

  public JfgeApplication(JfgeLibGdxComponent component, String gameKey) {
    this.component = component;
    this.gameKey = gameKey;
  }

  @Override
  public void create() {
    graphicsProvider = component.libGdxGraphicsProvider();
    graphicsProvider.initialize();

    controller1 = component.libGdxKeyboardController1();
    controller2 = component.libGdxKeyboardController2();
    engine = component.engine();
    game = component.games().get(gameKey);
    game.start();
  }

  @Override
  public void render() {
    if (engine == null) return;

    graphicsProvider.beginFrame();
    controller1.update();
    controller2.update();
    engine.tick();
  }

  @Override
  public void dispose() {
    if (game != null) {
      game.end();
    }
    if (graphicsProvider != null) {
      graphicsProvider.dispose();
    }
  }
}
