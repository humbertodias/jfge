package org.jfge.games.mk2.render;

import dagger.Binds;
import dagger.Module;
import javax.inject.Named;
import org.jfge.spi.render.ArenaRenderer;

@Module
public abstract class MortalKombat2RenderModule {

  @Binds
  @Named("arenaRenderer.mortalKombat2")
  abstract ArenaRenderer bindArenaRenderer(MortalKombat2ArenaRenderer renderer);
}
