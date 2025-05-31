package org.jfge.api.arena;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ArenaModule {

  @Binds
  abstract ArenaFactory bindArenaFactory(ArenaFactoryImpl impl);
}
