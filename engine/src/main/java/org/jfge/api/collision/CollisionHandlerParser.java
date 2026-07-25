package org.jfge.api.collision;

import java.io.IOException;

/** The Class CollisionHandlerParser. */
public interface CollisionHandlerParser {

  CollisionHandler parseFromXmlFile(String file) throws IOException;
}
