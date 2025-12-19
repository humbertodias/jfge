package org.jfge.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jfge.api.ai.AiModule;
import org.jfge.api.arena.ArenaModule;
import org.jfge.api.collision.CollisionModule;
import org.jfge.api.effect.EffectModule;
import org.jfge.api.engine.EngineModule;
import org.jfge.api.fighter.FighterModule;
import org.jfge.api.game.GameModule;
import org.jfge.api.projectile.ProjectileModule;
import org.jfge.api.render.RenderModule;
import org.jfge.ext.physics.PhysicsModule;
import org.jfge.ext.scene.SceneModule;
import org.jfge.games.mk2.arena.MortalKombat2ArenaModule;
import org.jfge.games.mk2.collision.MortalKombat2CollisionModule;
import org.jfge.games.mk2.fighter.MortalKombat2FighterModule;
import org.jfge.games.mk2.game.MortalKombat2GameModule;
import org.jfge.games.mk2.projectile.MortalKombat2ProjectileModule;
import org.jfge.games.mk2.render.MortalKombat2RenderModule;
import org.jfge.libgdx.graphics.LibGdxGraphicsModule;

public class DesktopLauncherMK2 {
  public static void main(String[] args) {
    // Create Guice injector with all modules
    Injector injector =
        Guice.createInjector(
            new EngineModule(),
            new LibGdxGraphicsModule(),
            new CollisionModule(),
            new EffectModule(),
            new PhysicsModule(),
            new FighterModule(),
            new SceneModule(),
            new AiModule(),
            new ProjectileModule(),
            new ArenaModule(),
            new RenderModule(),
            new GameModule(),
            // Mortal Kombat 2 modules
            new MortalKombat2FighterModule(),
            new MortalKombat2ArenaModule(),
            new MortalKombat2RenderModule(),
            new MortalKombat2ProjectileModule(),
            new MortalKombat2CollisionModule(),
            new MortalKombat2GameModule());

    // Configure libGDX
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Mortal Kombat 2 - JFGE");
    config.setWindowedMode(480, 272); // Match JFGE's expected resolution
    config.setForegroundFPS(20); // Match JFGE's frame rate

    // Create and launch the application
    new Lwjgl3Application(new JfgeApplicationAdapter(injector, "mortalKombat2"), config);
  }
}
