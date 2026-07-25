package org.jfge.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesLoader {

  private PropertiesLoader() {}

  public static Properties load(String resourcePath) {
    Properties properties = new Properties();
    try (InputStream stream = PropertiesLoader.class.getResourceAsStream(resourcePath)) {
      if (stream != null) {
        properties.load(stream);
      }
    } catch (IOException e) {
      throw new IllegalStateException("Failed to load " + resourcePath, e);
    }
    return properties;
  }

  public static int getInt(Properties properties, String key) {
    return Integer.parseInt(properties.getProperty(key));
  }

  public static boolean getBoolean(Properties properties, String key) {
    return Boolean.parseBoolean(properties.getProperty(key));
  }
}
