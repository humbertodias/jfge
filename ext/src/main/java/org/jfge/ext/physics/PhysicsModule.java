package org.jfge.ext.physics;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.spi.physics.SpritePhysics;

@Module(includes = PhysicsPropertiesModule.class)
public abstract class PhysicsModule {

  @Provides
  @IntoMap
  @StringKey("moveForward")
  static SpritePhysics moveForward(MoveForward moveForward) {
    return moveForward;
  }

  @Provides
  @IntoMap
  @StringKey("moveBackward")
  static SpritePhysics moveBackward(MoveBackward moveBackward) {
    return moveBackward;
  }

  @Provides
  @IntoMap
  @StringKey("jump")
  static SpritePhysics jump(Jump jump) {
    return jump;
  }

  @Provides
  @IntoMap
  @StringKey("jumpForward")
  static SpritePhysics jumpForward(JumpForward jumpForward) {
    return jumpForward;
  }

  @Provides
  @IntoMap
  @StringKey("jumpBackward")
  static SpritePhysics jumpBackward(JumpBackward jumpBackward) {
    return jumpBackward;
  }

  @Provides
  @IntoMap
  @StringKey("flying")
  static SpritePhysics flying(Flying flying) {
    return flying;
  }
}
