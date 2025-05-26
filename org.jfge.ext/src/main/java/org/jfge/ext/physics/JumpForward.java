package org.jfge.ext.physics;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

/** The Class JumpForward. */
public final class JumpForward implements SpritePhysics<Fighter> {

  /** The FLOOR. */
  private final int FLOOR = 240;

  /** The sprite. */
  private Fighter parent;

  /** The HORIZONTAL. */
  private final int HORIZONTAL;

  /** The VERTICAL. */
  private final int VERTICAL;

  private int direction;

  /**
   * Instantiates a new jump forward.
   *
   * @param horizontal the horizontal
   * @param vertical the vertical
   */
  @Inject
  public JumpForward(
      @Named("physics.fighter.jump.horizontal") int horizontal,
      @Named("physics.fighter.jump.vertical") int vertical) {
    this.HORIZONTAL = horizontal;
    this.VERTICAL = vertical;
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
      parent.setDx(HORIZONTAL);
    } else {
      parent.setDx(-HORIZONTAL);
    }

    if (parent.getDy() > 0) {
      parent.setDy(VERTICAL);
    } else {
      parent.setDy(-VERTICAL);
    }

    if (parent.getY() - parent.getHeight() < 0) {
      parent.setDy(VERTICAL);
    }

    if (parent.getY() + parent.getDy() > FLOOR) {
      parent.setDx(0);
      parent.setDy(0);
      parent.setY(FLOOR);
      parent.nextState();
    }
  }

  @Override
  public void init() {
    if (this.parent == null) return;

    this.direction = parent.getDirection();
  }
}
