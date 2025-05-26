package org.jfge.games.sf2.collision;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.jfge.api.collision.CollisionHandler;
import org.jfge.api.collision.CollisionHandlerParser;
import org.xml.sax.SAXException;

/** The Class sf2FighterCollisions. */
@Singleton
public final class Sf2ProjectileCollisions implements Provider<CollisionHandler> {

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
  private Sf2ProjectileCollisions(CollisionHandlerParser collisionHandlerParser) {
    this.collisionHandlerParser = collisionHandlerParser;
  }

  /* (non-Javadoc)
   * @see com.google.inject.Provider#get()
   */
  public CollisionHandler get() {
    if (collisionHandler == null) {
      try {
        this.collisionHandler =
            collisionHandlerParser.parseFromXmlFile(
                "/org/jfge/games/sf2/collision/sf2ProjectileCollisions.xml");
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      } catch (SAXException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return this.collisionHandler;
  }
}
