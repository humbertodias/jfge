package org.jfge.games.sf2.game;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Singleton; // StreetFighter2Game provider is a Singleton
import org.jfge.api.game.Game;
import org.jfge.api.game.GameFactory;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.arena.Arena;
import java.util.Map;
import javax.inject.Provider; // For map values

@Module
public class StreetFighter2GameModule {

  // StreetFighter2Game is a Provider<Game> and is annotated with @Singleton.
  // Dagger will manage its single instance.
  @Provides
  @Singleton // Ensure the StreetFighter2Game provider instance is a singleton
  StreetFighter2Game provideStreetFighter2Game(
      GameFactory gameFactory,
      Map<String, Provider<Fighter>> fighterProviders,
      Map<String, Provider<Arena>> arenaProviders) {
    return new StreetFighter2Game(gameFactory, fighterProviders, arenaProviders);
  }

  @Provides
  @IntoMap
  @StringKey("streetFighter2")
  Game provideGame(StreetFighter2Game gameProvider) {
    // The Game instance from gameProvider.get() will be cached within StreetFighter2Game
    return gameProvider.get();
  }
}
