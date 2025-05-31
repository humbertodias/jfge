package org.jfge.games.sf2.collision;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Singleton;
import org.jfge.api.collision.CollisionHandler;

@Module
public class StreetFighter2CollisionModule {

  // Sf2FighterCollisions and Sf2ProjectileCollisions are Providers<CollisionHandler>
  // and are already annotated with @Singleton. Dagger will manage their single instance.

  @Provides
  @Singleton // Ensures the CollisionHandler instance from the provider is a singleton
  @IntoMap
  @StringKey("streetFighter2FighterCollisions")
  CollisionHandler provideSf2FighterCollisions(Sf2FighterCollisions provider) {
    return provider.get();
  }

  @Provides
  @Singleton // Ensures the CollisionHandler instance from the provider is a singleton
  @IntoMap
  @StringKey("streetFighter2ProjectileCollisions")
  CollisionHandler provideSf2ProjectileCollisions(Sf2ProjectileCollisions provider) {
    return provider.get();
  }
}
