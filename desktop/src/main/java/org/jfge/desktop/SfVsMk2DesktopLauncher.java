package org.jfge.desktop;

import com.google.inject.Module;
import org.jfge.games.mk2.arena.MortalKombat2ArenaModule;
import org.jfge.games.mk2.effect.MortalKombat2EffectModule;
import org.jfge.games.mk2.fighter.MortalKombat2FighterModule;
import org.jfge.games.mk2.game.MortalKombat2GameModule;
import org.jfge.games.mk2.projectile.MortalKombat2ProjectileModule;
import org.jfge.games.mk2.render.MortalKombat2RenderModule;
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.game.StreetFighter2GameModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;
import org.jfge.games.sfvsmk2.arena.SfVsMk2ArenaModule;
import org.jfge.games.sfvsmk2.collision.SfVsMk2CollisionModule;
import org.jfge.games.sfvsmk2.game.SfVsMk2GameModule;
import org.jfge.libgdx.JfgeBootstrap;

public final class SfVsMk2DesktopLauncher {

  private SfVsMk2DesktopLauncher() {}

  public static void main(String[] args) {
    Module[] modules =
        JfgeBootstrap.combine(
            JfgeBootstrap.baseModules(),
            new StreetFighter2FighterModule(),
            new StreetFighter2ArenaModule(),
            new StreetFighter2RenderModule(),
            new StreetFighter2ProjectileModule(),
            new StreetFighter2CollisionModule(),
            new StreetFighter2GameModule(),
            new MortalKombat2ArenaModule(),
            new MortalKombat2FighterModule(),
            new MortalKombat2EffectModule(),
            new MortalKombat2ProjectileModule(),
            new MortalKombat2RenderModule(),
            new MortalKombat2GameModule(),
            new SfVsMk2ArenaModule(),
            new SfVsMk2GameModule(),
            new SfVsMk2CollisionModule());

    DesktopLauncher.launch(modules, "sfVsMk2");
  }
}
