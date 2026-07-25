package org.jfge.api.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.InputStream;

public final class ResourceLoader {

  private ResourceLoader() {}

  public static InputStream openStream(String resourcePath) {
    String classpathPath = resourcePath.startsWith("/") ? resourcePath : "/" + resourcePath;
    String internalPath = stripLeadingSlash(resourcePath);

    InputStream stream = ResourceLoader.class.getResourceAsStream(classpathPath);
    if (stream != null) {
      return stream;
    }

    ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
    if (contextLoader != null) {
      stream = contextLoader.getResourceAsStream(internalPath);
      if (stream != null) {
        return stream;
      }
      stream = contextLoader.getResourceAsStream(classpathPath);
      if (stream != null) {
        return stream;
      }
    }

    if (Gdx.files != null) {
      FileHandle internal = Gdx.files.internal(internalPath);
      if (internal.exists()) {
        return internal.read();
      }

      FileHandle classpath = Gdx.files.classpath(internalPath);
      if (classpath.exists()) {
        return classpath.read();
      }
    }

    return null;
  }

  private static String stripLeadingSlash(String path) {
    return path.startsWith("/") ? path.substring(1) : path;
  }
}
