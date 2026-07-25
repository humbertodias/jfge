package org.jfge.api.effect;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import java.util.List;
import org.jfge.spi.graphics.Image;

@AssistedFactory
public interface CollisionEffectFactory {

  CollisionEffectImpl create(
      List<Image> images, @Assisted("relX") double relX, @Assisted("relY") double relY);
}
