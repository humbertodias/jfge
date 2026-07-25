package org.jfge.games.sfvsmk2.game;

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
import org.jfge.games.mk2.arena.MortalKombat2ArenaModule;
import org.jfge.games.mk2.collision.MortalKombat2CollisionModule;
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
      StreetFighter2GameModule.class,
      MortalKombat2ArenaModule.class,
      MortalKombat2FighterModule.class,
      MortalKombat2EffectModule.class,
      MortalKombat2ProjectileModule.class,
      MortalKombat2RenderModule.class,
      MortalKombat2GameModule.class,
      SfVsMk2ArenaModule.class,
      SfVsMk2GameModule.class,
      SfVsMk2CollisionModule.class
    })
public interface SfVsMk2J2SeComponent {

  Map<String, Game> games();
}
