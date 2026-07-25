package org.jfge.api.config;

import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Named;

@Module
public abstract class EnginePropertiesModule {

  private static final Properties PROPS =
      PropertiesLoader.load("/org/jfge/config/engine/engine.properties");

  @Provides
  @Named("engine.fps")
  static int fps() {
    return PropertiesLoader.getInt(PROPS, "engine.fps");
  }

  @Provides
  @Named("engine.width")
  static int width() {
    return PropertiesLoader.getInt(PROPS, "engine.width");
  }

  @Provides
  @Named("engine.height")
  static int height() {
    return PropertiesLoader.getInt(PROPS, "engine.height");
  }

  @Provides
  @Named("engine.nodelays")
  static int noDelays() {
    return PropertiesLoader.getInt(PROPS, "engine.nodelays");
  }

  @Provides
  @Named("engine.frameskip")
  static int frameSkip() {
    return PropertiesLoader.getInt(PROPS, "engine.frameskip");
  }

  @Provides
  @Named("engine.db")
  static boolean dbEnabled() {
    return PropertiesLoader.getBoolean(PROPS, "engine.db");
  }
}
