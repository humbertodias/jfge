package org.jfge.libgdx;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public final class LibGdxEngineModule extends AbstractModule {

  @Override
  protected void configure() {
    bindConstant().annotatedWith(Names.named("engine.externalLoop")).to(true);
  }
}
