package org.jfge.games.mk2.collision;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import org.jfge.api.collision.CollisionHandler;

@Module
public abstract class MortalKombat2CollisionModule {

  @Provides
  @IntoMap
  @StringKey("mortalKombat2FighterCollisions")
  static CollisionHandler mortalKombat2FighterCollisions(
      MortalKombat2FighterCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoMap
  @StringKey("mortalKombat2ProjectileCollisions")
  static CollisionHandler mortalKombat2ProjectileCollisions(
      MortalKomat2ProjectileCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoSet
  static CollisionHandler mortalKombat2FighterCollisionHandler(
      MortalKombat2FighterCollisions collisions) {
    return collisions.get();
  }

  @Provides
  @IntoSet
  static CollisionHandler mortalKombat2ProjectileCollisionHandler(
      MortalKomat2ProjectileCollisions collisions) {
    return collisions.get();
  }
}
