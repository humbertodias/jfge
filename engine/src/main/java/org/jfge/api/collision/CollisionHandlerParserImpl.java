package org.jfge.api.collision;

import com.badlogic.gdx.utils.XmlReader.Element;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import org.jfge.api.xml.XmlResources;

@Singleton
public final class CollisionHandlerParserImpl implements CollisionHandlerParser {

  private final Logger logger;
  private final CollisionHandlerFactory collisionHandlerFactory;

  @Inject
  public CollisionHandlerParserImpl(
      Logger logger, CollisionHandlerFactory collisionHandlerFactory) {
    this.logger = logger;
    this.collisionHandlerFactory = collisionHandlerFactory;
  }

  @Override
  public CollisionHandler parseFromXmlFile(String file) throws IOException {
    Element root = XmlResources.parse(getClass(), file);
    HashMap<List<String>, String> collisions = new HashMap<>();

    for (Element collision : root.getChildrenByName("collision")) {
      List<String> tuple = new ArrayList<>();
      tuple.add(collision.getAttribute("curState"));
      tuple.add(collision.getAttribute("colState"));
      collisions.put(tuple, collision.getAttribute("reaction"));
    }

    return collisionHandlerFactory.create(collisions);
  }
}
