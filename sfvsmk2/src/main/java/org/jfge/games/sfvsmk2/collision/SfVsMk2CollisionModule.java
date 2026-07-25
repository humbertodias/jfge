package org.jfge.games.sfvsmk2.collision;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import org.jfge.api.collision.CollisionHandler;

@Module
public abstract class SfVsMk2CollisionModule {

  @Provides
  @IntoMap
  @StringKey("sfvsmk2FighterCollisions")
  static CollisionHandler sfvsmk2FighterCollisions(SfVsMk2FighterCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoMap
  @StringKey("sfvsmk2ProjectileCollisions")
  static CollisionHandler sfvsmk2ProjectileCollisions(SfVsMk2ProjectileCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoSet
  static CollisionHandler sfvsmk2FighterCollisionHandler(SfVsMk2FighterCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoSet
  static CollisionHandler sfvsmk2ProjectileCollisionHandler(
      SfVsMk2ProjectileCollisions collisions) {
    return collisions.get();
  }
}
