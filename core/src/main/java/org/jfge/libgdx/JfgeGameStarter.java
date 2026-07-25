package org.jfge.libgdx;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.util.Map;
import org.jfge.api.game.Game;

public final class JfgeGameStarter {

  private JfgeGameStarter() {}

  public static JfgeApplication.GameStarter forGameKey(String gameKey) {
    return injector -> {
      Map<String, Game> games =
          injector.getInstance(Key.get(new TypeLiteral<Map<String, Game>>() {}));
      Game game = games.get(gameKey);
      game.start();
      return game;
    };
  }
}
