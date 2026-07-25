package org.jfge.games.mk2.projectile;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.projectile.Projectile;

@Module
public abstract class MortalKombat2ProjectileModule {

  @Provides
  @IntoMap
  @StringKey("liukangFireball")
  static Projectile liukangFireball(LiuKangFireball provider) {
    return provider.get();
  }

  @Provides
  @IntoMap
  @StringKey("kanoFireball")
  static Projectile kanoFireball(KanoFireball provider) {
    return provider.get();
  }

  @Provides
  @IntoMap
  @StringKey("johnnyCageFireball")
  static Projectile johnnyCageFireball(JohnnyCageFireball provider) {
    return provider.get();
  }
}
