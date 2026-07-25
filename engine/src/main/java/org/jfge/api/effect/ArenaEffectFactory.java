package org.jfge.api.effect;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import java.util.List;
import org.jfge.spi.graphics.Image;

/** A factory for creating ArenaEffect objects. */
@AssistedFactory
public interface ArenaEffectFactory {

  ArenaEffectImpl create(List<Image> images, @Assisted("x") int x, @Assisted("y") int y);
}
