package org.jfge.api.config;

import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Named;

@Module
public abstract class FighterPropertiesModule {

  private static final Properties PROPS =
      PropertiesLoader.load("/org/jfge/config/fighter/fighter.properties");

  @Provides
  @Named("fighter.inputqueue.clear")
  static int inputQueueClear() {
    return PropertiesLoader.getInt(PROPS, "fighter.inputqueue.clear");
  }

  @Provides
  @Named("fighter.inputqueue.maxsize")
  static int inputQueueMaxSize() {
    return PropertiesLoader.getInt(PROPS, "fighter.inputqueue.maxsize");
  }
}
