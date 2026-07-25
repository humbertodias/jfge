package org.jfge.games.mk2.game;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.game.Game;

@Module
public abstract class MortalKombat2GameModule {

  @Provides
  @IntoMap
  @StringKey("mortalKombat2")
  static Game game(MortalKombat2Game provider) {
    return provider.get();
  }
}
