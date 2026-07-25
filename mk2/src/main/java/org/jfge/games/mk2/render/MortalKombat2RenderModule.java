package org.jfge.games.mk2.render;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.jfge.spi.render.ArenaRenderer;

public class MortalKombat2RenderModule extends AbstractModule {

  @Override
  protected void configure() {
    /*
     * binding render implementations
     */
    bind(ArenaRenderer.class)
        .annotatedWith(Names.named("arenaRenderer.mortalKombat2"))
        .to(MortalKombat2ArenaRenderer.class);
  }
}
