package org.jfge.games.sf2.arena;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import java.io.IOException;
import org.jfge.api.arena.Arena;
import org.jfge.api.arena.ArenaFactory;
import org.jfge.spi.render.ArenaRenderer;

public class RyuNight implements Provider<Arena> {

  private ArenaRenderer arenaRenderer;

  private ArenaFactory arenaFactory;

  private Arena arena;

  @Inject
  public RyuNight(
      @Named("arenaRenderer.streetFighter2") ArenaRenderer arenaRenderer,
      ArenaFactory arenaFactory) {
    this.arenaRenderer = arenaRenderer;
    this.arenaFactory = arenaFactory;
  }

  @Override
  public Arena get() {
    if (this.arena == null) {
      try {
        this.arena =
            arenaFactory.createArena(
                "/org/jfge/games/sf2/arena/images/ryuNight.png", arenaRenderer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return this.arena;
  }
}
