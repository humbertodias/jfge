package org.jfge.api.collision;

import org.jfge.api.engine.Updatable;
import org.jfge.api.fighter.Fighter;

/** The Interface CollisionDectector. */
public interface CollisionDetector extends Updatable {

  /**
   * Adds the sprite.
   *
   * @param fighter the sprite
   */
  public void addFighter(Fighter fighter);

  /**
   * Removes the sprite.
   *
   * @param fighter the sprite
   */
  public void removeFighter(Fighter fighter);
}
