package org.jfge.ext.physics;

import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Named;
import org.jfge.api.config.PropertiesLoader;

@Module
public abstract class PhysicsPropertiesModule {

  private static Properties props() {
    return PropertiesLoader.load("/org/jfge/config/physics/physics.properties");
  }

  @Provides
  @Named("physics.fighter.walk")
  static int fighterWalk() {
    return PropertiesLoader.getInt(props(), "physics.fighter.walk");
  }

  @Provides
  @Named("physics.fighter.jump.horizontal")
  static int fighterJumpHorizontal() {
    return PropertiesLoader.getInt(props(), "physics.fighter.jump.horizontal");
  }

  @Provides
  @Named("physics.fighter.jump.vertical")
  static int fighterJumpVertical() {
    return PropertiesLoader.getInt(props(), "physics.fighter.jump.vertical");
  }

  @Provides
  @Named("physics.projectile.flying")
  static int projectileFlying() {
    return PropertiesLoader.getInt(props(), "physics.projectile.flying");
  }
}
