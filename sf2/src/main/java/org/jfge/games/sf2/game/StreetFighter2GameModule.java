package org.jfge.games.sf2.game;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.game.Game;

@Module
public abstract class StreetFighter2GameModule {

  @Provides
  @IntoMap
  @StringKey("streetFighter2")
  static Game game(StreetFighter2Game provider) {
    return provider.get();
  }
}
