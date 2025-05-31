package org.jfge.api.effect;

import dagger.assisted.AssistedFactory;
import java.util.List;
import org.jfge.spi.graphics.Image;

/** A factory for creating ArenaEffect objects. */
@AssistedFactory
public interface ArenaEffectFactory {

  /**
   * Creates a new ArenaEffect object.
   *
   * @param images the images
   * @return the arena effect
   */
  ArenaEffect createArenaEffect(List<Image> images, int x, int y);
}
