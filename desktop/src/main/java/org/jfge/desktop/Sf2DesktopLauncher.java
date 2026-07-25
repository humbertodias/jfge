package org.jfge.desktop;

import com.google.inject.Module;
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.game.StreetFighter2GameModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;
import org.jfge.libgdx.JfgeBootstrap;

public final class Sf2DesktopLauncher {

  private Sf2DesktopLauncher() {}

  public static void main(String[] args) {
    Module[] modules =
        JfgeBootstrap.combine(
            JfgeBootstrap.baseModules(),
            new StreetFighter2FighterModule(),
            new StreetFighter2ArenaModule(),
            new StreetFighter2RenderModule(),
            new StreetFighter2ProjectileModule(),
            new StreetFighter2CollisionModule(),
            new StreetFighter2GameModule());

    DesktopLauncher.launch(modules, "streetFighter2");
  }
}
