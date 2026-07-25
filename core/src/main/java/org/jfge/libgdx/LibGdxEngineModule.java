package org.jfge.libgdx;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public abstract class LibGdxEngineModule {

  @Provides
  @Named("engine.externalLoop")
  static boolean externalLoop() {
    return true;
  }
}
