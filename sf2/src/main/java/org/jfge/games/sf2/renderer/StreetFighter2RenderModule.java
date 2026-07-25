package org.jfge.games.sf2.renderer;

import dagger.Binds;
import dagger.Module;
import javax.inject.Named;
import org.jfge.spi.render.ArenaRenderer;

@Module
public abstract class StreetFighter2RenderModule {

  @Binds
  @Named("arenaRenderer.streetFighter2")
  abstract ArenaRenderer bindArenaRenderer(StreetFighter2ArenaRenderer renderer);
}
