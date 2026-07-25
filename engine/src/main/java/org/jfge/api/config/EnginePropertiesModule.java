package org.jfge.api.config;

import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Named;

@Module
public abstract class EnginePropertiesModule {

  private static Properties props() {
    return PropertiesLoader.load("/org/jfge/config/engine/engine.properties");
  }

  @Provides
  @Named("engine.fps")
  static int fps() {
    return PropertiesLoader.getInt(props(), "engine.fps");
  }

  @Provides
  @Named("engine.width")
  static int width() {
    return PropertiesLoader.getInt(props(), "engine.width");
  }

  @Provides
  @Named("engine.height")
  static int height() {
    return PropertiesLoader.getInt(props(), "engine.height");
  }

  @Provides
  @Named("engine.nodelays")
  static int noDelays() {
    return PropertiesLoader.getInt(props(), "engine.nodelays");
  }

  @Provides
  @Named("engine.frameskip")
  static int frameSkip() {
    return PropertiesLoader.getInt(props(), "engine.frameskip");
  }

  @Provides
  @Named("engine.db")
  static boolean dbEnabled() {
    return PropertiesLoader.getBoolean(props(), "engine.db");
  }
}
