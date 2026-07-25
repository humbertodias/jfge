package org.jfge.api.xml;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import java.io.IOException;
import java.io.InputStream;
import org.jfge.api.config.ResourceLoader;

public final class XmlResources {

  private XmlResources() {}

  public static Element parse(Class<?> anchor, String resourcePath) throws IOException {
    InputStream stream = ResourceLoader.openStream(resourcePath);
    if (stream == null) {
      stream = anchor.getResourceAsStream(resourcePath);
    }
    if (stream == null) {
      throw new IOException("Resource not found: " + resourcePath);
    }

    try (InputStream in = stream) {
      return new XmlReader().parse(in);
    }
  }
}
