package org.jfge.games.mk2.arena;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.arena.Arena;

@Module
public abstract class MortalKombat2ArenaModule {

  @Provides
  @IntoMap
  @StringKey("deadPool")
  static Arena deadPool(DeadPool deadPool) {
    return deadPool.get();
  }
}
