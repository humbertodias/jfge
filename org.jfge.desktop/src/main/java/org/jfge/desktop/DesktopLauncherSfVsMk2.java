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
import org.jfge.games.sfvsmk2.arena.SfVsMk2ArenaModule;
import org.jfge.games.sfvsmk2.collision.SfVsMk2CollisionModule;
import org.jfge.games.sfvsmk2.fighter.SfVsMk2FighterModule;
import org.jfge.games.sfvsmk2.game.SfVsMk2GameModule;
import org.jfge.games.sfvsmk2.projectile.SfVsMk2ProjectileModule;
import org.jfge.games.sfvsmk2.renderer.SfVsMk2RenderModule;
import org.jfge.libgdx.graphics.LibGdxGraphicsModule;

public class DesktopLauncherSfVsMk2 {
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
            // SF vs MK2 modules
            new SfVsMk2FighterModule(),
            new SfVsMk2ArenaModule(),
            new SfVsMk2RenderModule(),
            new SfVsMk2ProjectileModule(),
            new SfVsMk2CollisionModule(),
            new SfVsMk2GameModule());

    // Configure libGDX
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Street Fighter vs Mortal Kombat 2 - JFGE");
    config.setWindowedMode(640, 480);
    config.setForegroundFPS(60);

    // Create and launch the application
    new Lwjgl3Application(new JfgeApplicationAdapter(injector, "sfVsMk2"), config);
  }
}
