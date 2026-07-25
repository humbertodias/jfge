package org.jfge.games.sfvsmk2.arena;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.inject.Named;
import java.io.IOException;
import org.jfge.api.arena.Arena;
import org.jfge.api.arena.ArenaFactory;
import org.jfge.spi.render.ArenaRenderer;

@Singleton
public final class SfVsMk2Arena implements Provider<Arena> {

  private ArenaRenderer arenaRenderer;

  private ArenaFactory arenaFactory;

  private Arena arena;

  @Inject
  public SfVsMk2Arena(
      @Named("arenaRenderer.mortalKombat2") ArenaRenderer arenaRenderer,
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
                "/org/jfge/games/sfvsmk2/arena/images/sfvsmk.png", arenaRenderer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return this.arena;
  }
}
