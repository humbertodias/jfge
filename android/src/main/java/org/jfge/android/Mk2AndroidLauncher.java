package org.jfge.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.inject.Module;
import org.jfge.games.mk2.arena.MortalKombat2ArenaModule;
import org.jfge.games.mk2.collision.MortalKombat2CollisionModule;
import org.jfge.games.mk2.effect.MortalKombat2EffectModule;
import org.jfge.games.mk2.fighter.MortalKombat2FighterModule;
import org.jfge.games.mk2.game.MortalKombat2GameModule;
import org.jfge.games.mk2.projectile.MortalKombat2ProjectileModule;
import org.jfge.games.mk2.render.MortalKombat2RenderModule;
import org.jfge.libgdx.JfgeApplication;
import org.jfge.libgdx.JfgeBootstrap;
import org.jfge.libgdx.JfgeGameStarter;

public class Mk2AndroidLauncher extends AndroidApplication {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useImmersiveMode(true);
    config.useAccelerometer = false;
    config.useCompass = false;

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

    initialize(new JfgeApplication(modules, JfgeGameStarter.forGameKey("mortalKombat2")), config);
  }
}
