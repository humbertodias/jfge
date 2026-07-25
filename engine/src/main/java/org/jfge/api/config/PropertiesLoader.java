package org.jfge.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertiesLoader {

  private static final Map<String, Properties> CACHE = new HashMap<>();

  private PropertiesLoader() {}

  public static Properties load(String resourcePath) {
    synchronized (CACHE) {
      Properties cached = CACHE.get(resourcePath);
      if (cached != null) {
        return cached;
      }

      Properties properties = new Properties();
      try (InputStream stream = ResourceLoader.openStream(resourcePath)) {
        if (stream == null) {
          throw new IllegalStateException("Properties resource not found: " + resourcePath);
        }
        properties.load(stream);
      } catch (IOException e) {
        throw new IllegalStateException("Failed to load properties from " + resourcePath, e);
      }

      CACHE.put(resourcePath, properties);
      return properties;
    }
  }

  public static int getInt(Properties properties, String key) {
    String value = properties.getProperty(key);
    if (value == null) {
      throw new IllegalStateException("Missing property: " + key);
    }
    return Integer.parseInt(value);
  }

  public static boolean getBoolean(Properties properties, String key) {
    String value = properties.getProperty(key);
    if (value == null) {
      throw new IllegalStateException("Missing property: " + key);
    }
    return Boolean.parseBoolean(value);
  }
}
