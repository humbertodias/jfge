package org.jfge.api.game;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GameModule {

  @Binds
  abstract GameFactory bindGameFactory(GameFactoryImpl impl);
}
