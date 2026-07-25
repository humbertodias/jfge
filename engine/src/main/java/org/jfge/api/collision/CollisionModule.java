package org.jfge.api.collision;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import org.jfge.spi.collision.CollisionDetectionStrategy;

@Module
public abstract class CollisionModule {

  @Binds
  abstract CollisionHandlerParser bindCollisionHandlerParser(CollisionHandlerParserImpl parser);

  @Binds
  abstract CollisionDetector bindCollisionDetector(CollisionDetectorImpl detector);

  @Binds
  @IntoSet
  abstract CollisionDetectionStrategy bindRectangleStrategy(RectangleCollisionStrategy strategy);
}
