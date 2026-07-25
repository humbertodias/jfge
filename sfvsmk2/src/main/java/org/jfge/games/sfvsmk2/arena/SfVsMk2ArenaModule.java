package org.jfge.games.sfvsmk2.arena;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.arena.Arena;

@Module
public abstract class SfVsMk2ArenaModule {

  @Provides
  @IntoMap
  @StringKey("sfVsMk2")
  static Arena sfVsMk2Arena(SfVsMk2Arena arena) {
    return arena.get();
  }
}
