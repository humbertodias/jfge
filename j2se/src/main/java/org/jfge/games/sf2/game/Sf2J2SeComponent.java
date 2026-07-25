package org.jfge.games.sf2.game;

import dagger.Component;
import java.util.Map;
import javax.inject.Singleton;
import org.jfge.api.ai.AiModule;
import org.jfge.api.arena.ArenaModule;
import org.jfge.api.collision.CollisionModule;
import org.jfge.api.effect.EffectModule;
import org.jfge.api.engine.EngineModule;
import org.jfge.api.fighter.FighterModule;
import org.jfge.api.game.Game;
import org.jfge.api.game.GameModule;
import org.jfge.api.projectile.ProjectileModule;
import org.jfge.api.render.RenderModule;
import org.jfge.ext.physics.PhysicsModule;
import org.jfge.ext.scene.SceneModule;
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;
import org.jfge.j2se.J2SeEngineModule;
import org.jfge.j2se.graphics.J2SeGraphicsModule;

@Singleton
@Component(
    modules = {
      EngineModule.class,
      J2SeEngineModule.class,
      J2SeGraphicsModule.class,
      CollisionModule.class,
      EffectModule.class,
      PhysicsModule.class,
      FighterModule.class,
      SceneModule.class,
      AiModule.class,
      ProjectileModule.class,
      ArenaModule.class,
      RenderModule.class,
      GameModule.class,
      StreetFighter2FighterModule.class,
      StreetFighter2ArenaModule.class,
      StreetFighter2RenderModule.class,
      StreetFighter2ProjectileModule.class,
      StreetFighter2CollisionModule.class,
      StreetFighter2GameModule.class
    })
public interface Sf2J2SeComponent {

  Map<String, Game> games();
}
