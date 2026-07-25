package org.jfge.games.sf2.collision;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import org.jfge.api.collision.CollisionHandler;
import org.jfge.api.collision.CollisionHandlerParser;

/** The Class sf2FighterCollisions. */
@Singleton
public final class Sf2FighterCollisions implements Provider<CollisionHandler> {

  /** The collision handler. */
  private CollisionHandler collisionHandler;

  /** The collision handler parser. */
  private CollisionHandlerParser collisionHandlerParser;

  /**
   * Instantiates a new sf2 fighter collisions.
   *
   * @param collisionHandlerParser the collision handler parser
   */
  @Inject
  public Sf2FighterCollisions(CollisionHandlerParser collisionHandlerParser) {
    this.collisionHandlerParser = collisionHandlerParser;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  public CollisionHandler get() {
    if (collisionHandler == null) {
      try {
        this.collisionHandler =
            collisionHandlerParser.parseFromXmlFile(
                "/org/jfge/games/sf2/collision/sf2FighterCollisions.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return this.collisionHandler;
  }
}
