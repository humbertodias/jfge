package org.jfge.games.sfvsmk2.collision;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import org.jfge.api.collision.CollisionHandler;
import org.jfge.api.collision.CollisionHandlerParser;

/** The Class SfVsMk2ProjectileCollisions. */
@Singleton
public class SfVsMk2ProjectileCollisions implements Provider<CollisionHandler> {

  /** The collision handler. */
  private CollisionHandler collisionHandler;

  /** The collision handler parser. */
  private CollisionHandlerParser collisionHandlerParser;

  /**
   * Instantiates a new sf vs mk2 projectile collisions.
   *
   * @param collisionHandlerParser the collision handler parser
   */
  @Inject
  public SfVsMk2ProjectileCollisions(CollisionHandlerParser collisionHandlerParser) {
    this.collisionHandlerParser = collisionHandlerParser;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  @Override
  public CollisionHandler get() {
    if (collisionHandler == null) {
      try {
        this.collisionHandler =
            collisionHandlerParser.parseFromXmlFile(
                "/org/jfge/games/sfvsmk2/collision/sfvsmk2ProjectileCollisions.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return this.collisionHandler;
  }
}
