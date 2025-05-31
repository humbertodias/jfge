package org.jfge.api.effect;

import dagger.Module;

@Module
public abstract class EffectModule {
  // CollisionEffectFactory is now an @AssistedFactory, Dagger provides it.
  // ArenaEffectFactory is now an @AssistedFactory, Dagger provides it.

  // Map<String, CollisionEffect> and Map<String, ArenaEffect> will be provided
  // if other modules contribute to them using @IntoMap, or they will be empty
  // if appropriately configured (e.g. @Multibinds abstract Map<K,V> provider()).
  // No explicit MapBinder declarations are needed in Dagger for the module itself
  // unless it's providing entries.
}
