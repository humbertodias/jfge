package org.jfge.api.collision;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import org.jfge.spi.collision.CollisionDetectionStrategy;

@Module
public abstract class CollisionModule {

  @Binds
  abstract CollisionHandlerParser bindCollisionHandlerParser(CollisionHandlerParserImpl impl);

  @Binds
  abstract CollisionDetector bindCollisionDetector(CollisionDetectorImpl impl);

  // CollisionHandlerFactory is now an @AssistedFactory, Dagger provides it.

  // The Set<CollisionHandler> will be provided if other modules contribute to it,
  // or it will be empty. No explicit binding needed here unless this module provides some.

  @Binds
  @IntoSet
  abstract CollisionDetectionStrategy bindRectangleCollisionStrategy(
      RectangleCollisionStrategy impl);
}
