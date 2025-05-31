package org.jfge.android.dagger;

import dagger.Component;
import java.util.Map;
import javax.inject.Named;
import javax.inject.Singleton;

// JFGE Core API Modules
import org.jfge.api.ai.AiModule;
import org.jfge.api.arena.ArenaModule;
import org.jfge.api.collision.CollisionModule;
import org.jfge.api.effect.EffectModule;
import org.jfge.api.engine.EngineModule;
import org.jfge.api.fighter.FighterModule;
import org.jfge.api.game.GameModule;
import org.jfge.api.projectile.ProjectileModule;
import org.jfge.api.render.RenderModule;

// JFGE Extensions Modules
import org.jfge.ext.physics.PhysicsModule;
import org.jfge.ext.scene.SceneModule;

// JFGE Android specific modules
import org.jfge.android.graphics.AndroidGraphicsModule;

// JFGE Games SF2 specific modules
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.game.StreetFighter2GameModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;

// JFGE SPI (Service Provider Interfaces) - for provision methods
import org.jfge.api.game.Game;
import org.jfge.spi.controller.Controller;

@Singleton
@Component(
    modules = {
      // Core API
      AiModule.class,
      ArenaModule.class,
      CollisionModule.class,
      EffectModule.class,
      EngineModule.class,
      FighterModule.class,
      GameModule.class,
      ProjectileModule.class,
      RenderModule.class,
      // Android
      AndroidGraphicsModule.class,
      // Extensions
      PhysicsModule.class,
      SceneModule.class,
      // SF2 Game
      StreetFighter2ArenaModule.class,
      StreetFighter2CollisionModule.class,
      StreetFighter2FighterModule.class,
      StreetFighter2GameModule.class,
      StreetFighter2ProjectileModule.class,
      StreetFighter2RenderModule.class
    })
public interface ApplicationComponent {
  // Provision methods for entry points
  Map<String, Game> games();

  @Named("keyboard.android")
  Controller androidKeyboardController();
}
