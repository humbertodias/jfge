package org.jfge.api.collision;

import dagger.assisted.AssistedFactory;
import java.util.List;
import java.util.Map;

/** A factory for creating CollisionHandler objects. */
@AssistedFactory
public interface CollisionHandlerFactory {

  CollisionHandlerImpl create(Map<List<String>, String> collisionMap);
}
