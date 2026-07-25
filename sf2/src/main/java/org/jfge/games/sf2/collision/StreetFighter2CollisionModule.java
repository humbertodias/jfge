package org.jfge.games.sf2.collision;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.jfge.api.collision.CollisionHandler;

public class StreetFighter2CollisionModule extends AbstractModule {

  @Override
  protected void configure() {
    MapBinder<String, CollisionHandler> collisionHandlerBinder =
        MapBinder.newMapBinder(binder(), String.class, CollisionHandler.class);
    collisionHandlerBinder
        .addBinding("streetFighter2FighterCollisions")
        .toProvider(Sf2FighterCollisions.class);
    collisionHandlerBinder
        .addBinding("streetFighter2ProjectileCollisions")
        .toProvider(Sf2ProjectileCollisions.class);
  }
}
