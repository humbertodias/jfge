package org.jfge.games.sfvsmk2.collision;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.jfge.api.collision.CollisionHandler;

public class SfVsMk2CollisionModule extends AbstractModule {

  @Override
  protected void configure() {
    MapBinder<String, CollisionHandler> collisionHandlerBinder =
        MapBinder.newMapBinder(binder(), String.class, CollisionHandler.class);
    collisionHandlerBinder
        .addBinding("sfvsmk2FighterCollisions")
        .toProvider(SfVsMk2FighterCollisions.class);
    collisionHandlerBinder
        .addBinding("sfvsmk2ProjectileCollisions")
        .toProvider(SfVsMk2ProjectileCollisions.class);
  }
}
