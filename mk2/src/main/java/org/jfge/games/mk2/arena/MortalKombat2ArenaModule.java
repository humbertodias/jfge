package org.jfge.games.mk2.arena;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.jfge.api.arena.Arena;

public class MortalKombat2ArenaModule extends AbstractModule {

  @Override
  protected void configure() {
    MapBinder<String, Arena> arenaBinder =
        MapBinder.newMapBinder(binder(), String.class, Arena.class);

    arenaBinder.addBinding("deadPool").toProvider(DeadPool.class);
  }
}
