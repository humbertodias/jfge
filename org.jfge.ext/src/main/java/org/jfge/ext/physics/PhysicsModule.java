package org.jfge.ext.physics;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.jfge.spi.physics.SpritePhysics;

@Module
public class PhysicsModule { // Changed to concrete class to provide @Provides methods

  // TODO: Load properties from /org/jfge/config/physics/physics.properties
  // Providing default values for now.

  @Provides
  @Named("physics.projectile.flying")
  static int provideProjectileFlyingVelocity() {
    return 5; // Example default
  }

  @Provides
  @Named("physics.fighter.jump.vertical")
  static int provideFighterJumpVerticalVelocity() {
    return 10; // Example default
  }

  @Provides
  @Named("physics.fighter.jump.horizontal")
  static int provideFighterJumpHorizontalVelocity() {
    return 3; // Example default
  }

  @Provides
  @Named("physics.fighter.walk")
  static int provideFighterWalkVelocity() {
    return 2; // Example default
  }

  @Provides
  @IntoMap
  @StringKey("moveForward")
  SpritePhysics provideMoveForward(MoveForward moveForward) {
    return moveForward;
  }

  @Provides
  @IntoMap
  @StringKey("moveBackward")
  SpritePhysics provideMoveBackward(MoveBackward moveBackward) {
    return moveBackward;
  }

  @Provides
  @IntoMap
  @StringKey("jump")
  SpritePhysics provideJump(Jump jump) {
    return jump;
  }

  @Provides
  @IntoMap
  @StringKey("jumpForward")
  SpritePhysics provideJumpForward(JumpForward jumpForward) {
    return jumpForward;
  }

  @Provides
  @IntoMap
  @StringKey("jumpBackward")
  SpritePhysics provideJumpBackward(JumpBackward jumpBackward) {
    return jumpBackward;
  }

  @Provides
  @IntoMap
  @StringKey("flying")
  SpritePhysics provideFlying(Flying flying) {
    return flying;
  }
}
