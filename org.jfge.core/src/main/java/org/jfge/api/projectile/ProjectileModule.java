package org.jfge.api.projectile;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProjectileModule {

  @Binds
  abstract ProjectileParser bindProjectileParser(ProjectileParserImpl impl);

  @Binds
  abstract ProjectileFactory bindProjectileFactory(ProjectileFactoryImpl impl);

  // MapBinder<String, Projectile> projectileBinder is not needed in Dagger.
  // If this map is injected, other modules should provide entries using @IntoMap.
  // If this module needs to provide an empty map, it can do so with:
  // @Multibinds abstract Map<String, Projectile> projectileMap();
}
