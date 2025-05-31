package org.jfge.api.fighter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
// TODO: remove unused imports after properties are handled
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.spi.physics.SpritePhysics;

/** The Class FighterModule. */
@Module
public abstract class FighterModule {

  @Binds
  abstract FighterFactory bindFighterFactory(FighterFactoryImpl impl);

  @Binds
  abstract FighterParser bindFighterParser(FighterParserImpl impl);

  @Binds
  abstract InputQueue bindInputQueue(BufferedInputQueue impl);

  // TODO: Figure out how to best load properties in Dagger.
  // For now, providing default values from fighter.properties.
  // Consider using a dedicated configuration object or reading properties at startup.

  @Provides
  @Named("fighter.inputqueue.clear")
  static int provideInputQueueClear() {
    // Default value, ideally from fighter.properties
    return 60; // Example default
  }

  @Provides
  @Named("fighter.inputqueue.maxsize")
  static int provideInputQueueMaxSize() {
    // Default value, ideally from fighter.properties
    return 10; // Example default
  }

  // MapBinder declarations for SpritePhysics, CollisionEffect, Projectile
  // are not needed in Dagger. If these maps are injected,
  // other modules should provide their entries using @IntoMap.
  // If this module needs to provide an empty map, it can do so with:
  // @Multibinds abstract Map<String, SpritePhysics> spritePhysicsMap();
  // etc. for the other maps. For now, assuming they are populated elsewhere or can be empty.
}
