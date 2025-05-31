package org.jfge.games.sf2.renderer;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.jfge.spi.render.ArenaRenderer;

@Module
public class StreetFighter2RenderModule {

  @Provides
  @Named("arenaRenderer.streetFighter2")
  ArenaRenderer provideStreetFighter2ArenaRenderer(
      StreetFighter2ArenaRenderer impl) { // StreetFighter2ArenaRenderer has @Inject constructor
    return impl;
  }
}
