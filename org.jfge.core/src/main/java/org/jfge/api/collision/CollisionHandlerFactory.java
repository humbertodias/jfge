package org.jfge.api.collision;

import dagger.assisted.AssistedFactory;
import java.util.List;
import java.util.Map;

/** A factory for creating CollisionHandler objects. */
@AssistedFactory
public interface CollisionHandlerFactory {

  /**
   * Creates a new CollisionHandler object.
   *
   * @param collisionMap the collision map
   * @return the collision handler
   */
  CollisionHandler createCollisionHandler(Map<List<String>, String> collisionMap);
}
