package org.jfge.api.projectile;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProjectileModule {

  @Binds
  abstract ProjectileParser bindProjectileParser(ProjectileParserImpl parser);

  @Binds
  abstract ProjectileFactory bindProjectileFactory(ProjectileFactoryImpl factory);
}
