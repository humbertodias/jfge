package org.jfge.ext.physics;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

/** The Class WalkForward. */
public final class MoveForward implements SpritePhysics<Fighter> {

  /** The sprite. */
  private Fighter parent;

  private final int WALK;

  private int direction;

  @Inject
  public MoveForward(@Named("physics.fighter.walk") int walk) {
    this.WALK = walk;
  }

  /* (non-Javadoc)
   * @see org.jfge.physics.SpritePhysics#setSprite(org.jfge.sprite.Sprite)
   */
  @Override
  public void setParent(Fighter sprite) {
    this.parent = sprite;
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Updatable#update()
   */
  @Override
  public void update() {
    if (parent == null) return;

    if (direction == Sprite.RIGHT) {
      parent.setDx(WALK);
    } else if (direction == Sprite.LEFT) {
      parent.setDx(-WALK);
    }
  }

  @Override
  public void init() {
    if (this.parent == null) return;

    this.direction = parent.getDirection();
  }
}
