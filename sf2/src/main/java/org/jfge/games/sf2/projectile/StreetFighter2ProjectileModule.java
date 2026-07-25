package org.jfge.games.sf2.projectile;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.projectile.Projectile;

@Module
public abstract class StreetFighter2ProjectileModule {

  @Provides
  @IntoMap
  @StringKey("hadouken")
  static Projectile hadouken(Hadouken provider) {
    return provider.get();
  }
}
