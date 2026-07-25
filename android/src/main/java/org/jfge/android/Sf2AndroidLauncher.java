package org.jfge.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.inject.Module;
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.game.StreetFighter2GameModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;
import org.jfge.libgdx.JfgeApplication;
import org.jfge.libgdx.JfgeBootstrap;
import org.jfge.libgdx.JfgeGameStarter;

public class Sf2AndroidLauncher extends AndroidApplication {

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
            new StreetFighter2FighterModule(),
            new StreetFighter2ArenaModule(),
            new StreetFighter2RenderModule(),
            new StreetFighter2ProjectileModule(),
            new StreetFighter2CollisionModule(),
            new StreetFighter2GameModule());

    initialize(new JfgeApplication(modules, JfgeGameStarter.forGameKey("streetFighter2")), config);
  }
}
