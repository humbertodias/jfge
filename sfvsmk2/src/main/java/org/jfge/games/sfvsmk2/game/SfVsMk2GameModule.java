package org.jfge.games.sfvsmk2.game;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.game.Game;

@Module
public abstract class SfVsMk2GameModule {

  @Provides
  @IntoMap
  @StringKey("sfVsMk2")
  static Game game(SfVsMk2Game provider) {
    return provider.get();
  }
}
