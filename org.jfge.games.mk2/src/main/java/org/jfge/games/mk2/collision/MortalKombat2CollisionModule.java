package org.jfge.games.mk2.collision;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.jfge.api.collision.CollisionHandler;

public class MortalKombat2CollisionModule extends AbstractModule {

  @Override
  protected void configure() {
    MapBinder<String, CollisionHandler> collisionHandlerBinder =
        MapBinder.newMapBinder(binder(), String.class, CollisionHandler.class);
    collisionHandlerBinder
        .addBinding("mortalKombat2FighterCollisions")
        .toProvider(MortalKombat2FighterCollisions.class);
    collisionHandlerBinder
        .addBinding("mortalKombat2ProjectileCollisions")
        .toProvider(MortalKomat2ProjectileCollisions.class);
  }
}
