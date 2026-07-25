package org.jfge.api.effect;

import com.google.inject.assistedinject.Assisted;
import java.util.List;
import org.jfge.spi.graphics.Image;

public interface CollisionEffectFactory {

  public CollisionEffect createCollisionEffect(
      List<Image> images, @Assisted("relX") double relX, @Assisted("relY") double relY);
}
