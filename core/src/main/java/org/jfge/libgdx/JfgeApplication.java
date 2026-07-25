package org.jfge.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import org.jfge.api.engine.Engine;
import org.jfge.api.game.Game;
import org.jfge.libgdx.controller.LibGdxKeyboardController1;
import org.jfge.libgdx.controller.LibGdxKeyboardController2;
import org.jfge.libgdx.graphics.LibGdxGraphicsProvider;

public final class JfgeApplication extends ApplicationAdapter {

  private final String gameKey;

  private JfgeLibGdxComponent component;
  private LibGdxGraphicsProvider graphicsProvider;
  private LibGdxKeyboardController1 controller1;
  private LibGdxKeyboardController2 controller2;
  private Engine engine;
  private Game game;

  public JfgeApplication(String gameKey) {
    this.gameKey = gameKey;
  }

  @Override
  public void create() {
    component = JfgeBootstrap.createComponent();

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

    controller1.update();
    controller2.update();
    engine.tick(Gdx.graphics.getDeltaTime());
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
