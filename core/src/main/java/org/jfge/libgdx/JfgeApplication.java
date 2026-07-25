package org.jfge.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.jfge.api.engine.Engine;
import org.jfge.api.game.Game;
import org.jfge.libgdx.controller.LibGdxKeyboardController1;
import org.jfge.libgdx.controller.LibGdxKeyboardController2;
import org.jfge.libgdx.graphics.LibGdxGraphicsProvider;

public final class JfgeApplication extends ApplicationAdapter {

  private final Module[] modules;
  private final GameStarter gameStarter;

  private Injector injector;
  private LibGdxGraphicsProvider graphicsProvider;
  private LibGdxKeyboardController1 controller1;
  private LibGdxKeyboardController2 controller2;
  private Engine engine;
  private Game game;

  public JfgeApplication(Module[] modules, GameStarter gameStarter) {
    this.modules = modules;
    this.gameStarter = gameStarter;
  }

  @Override
  public void create() {
    injector = Guice.createInjector(modules);

    graphicsProvider = injector.getInstance(LibGdxGraphicsProvider.class);
    graphicsProvider.initialize();

    controller1 = injector.getInstance(LibGdxKeyboardController1.class);
    controller2 = injector.getInstance(LibGdxKeyboardController2.class);
    engine = injector.getInstance(Engine.class);
    game = gameStarter.start(injector);
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

  @FunctionalInterface
  public interface GameStarter {
    Game start(Injector injector);
  }
}
