package org.jfge.api.effect;

import dagger.assisted.AssistedFactory;
import java.util.List;
import org.jfge.spi.graphics.Image;

@AssistedFactory
public interface CollisionEffectFactory {

  CollisionEffect createCollisionEffect(List<Image> images, double relX, double relY);
}
