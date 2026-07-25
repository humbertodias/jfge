package org.jfge.desktop;

import com.google.inject.Module;
import org.jfge.games.mk2.arena.MortalKombat2ArenaModule;
import org.jfge.games.mk2.collision.MortalKombat2CollisionModule;
import org.jfge.games.mk2.effect.MortalKombat2EffectModule;
import org.jfge.games.mk2.fighter.MortalKombat2FighterModule;
import org.jfge.games.mk2.game.MortalKombat2GameModule;
import org.jfge.games.mk2.projectile.MortalKombat2ProjectileModule;
import org.jfge.games.mk2.render.MortalKombat2RenderModule;
import org.jfge.libgdx.JfgeBootstrap;

public final class Mk2DesktopLauncher {

  private Mk2DesktopLauncher() {}

  public static void main(String[] args) {
    Module[] modules =
        JfgeBootstrap.combine(
            JfgeBootstrap.baseModules(),
            new MortalKombat2ArenaModule(),
            new MortalKombat2FighterModule(),
            new MortalKombat2CollisionModule(),
            new MortalKombat2EffectModule(),
            new MortalKombat2ProjectileModule(),
            new MortalKombat2RenderModule(),
            new MortalKombat2GameModule());

    DesktopLauncher.launch(modules, "mortalKombat2");
  }
}
