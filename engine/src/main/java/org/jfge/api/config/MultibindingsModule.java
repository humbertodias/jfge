package org.jfge.api.config;

import dagger.Module;
import dagger.multibindings.Multibinds;
import java.util.Map;
import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.game.Game;
import org.jfge.api.projectile.Projectile;
import org.jfge.spi.controller.Controller;

@Module
public abstract class MultibindingsModule {

  @Multibinds
  abstract Map<String, Controller> controllerMap();

  @Multibinds
  abstract Map<String, Game> gameMap();

  @Multibinds
  abstract Map<String, Projectile> projectileMap();

  @Multibinds
  abstract Map<String, CollisionEffect> collisionEffectMap();

  @Multibinds
  abstract Map<String, ArenaEffect> arenaEffectMap();
}
