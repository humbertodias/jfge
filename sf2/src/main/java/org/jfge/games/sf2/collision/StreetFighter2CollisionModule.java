package org.jfge.games.sf2.collision;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import org.jfge.api.collision.CollisionHandler;

@Module
public abstract class StreetFighter2CollisionModule {

  @Provides
  @IntoMap
  @StringKey("streetFighter2FighterCollisions")
  static CollisionHandler streetFighter2FighterCollisions(Sf2FighterCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoMap
  @StringKey("streetFighter2ProjectileCollisions")
  static CollisionHandler streetFighter2ProjectileCollisions(Sf2ProjectileCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoSet
  static CollisionHandler streetFighter2FighterCollisionHandler(Sf2FighterCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoSet
  static CollisionHandler streetFighter2ProjectileCollisionHandler(
      Sf2ProjectileCollisions collisions) {
    return collisions.get();
  }
}
