package org.jfge.j2se;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public abstract class J2SeEngineModule {

  @Provides
  @Named("engine.externalLoop")
  static boolean externalLoop() {
    return false;
  }
}
