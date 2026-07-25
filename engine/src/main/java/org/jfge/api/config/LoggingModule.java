package org.jfge.api.config;

import dagger.Module;
import dagger.Provides;
import java.util.logging.Logger;
import javax.inject.Singleton;

@Module
public abstract class LoggingModule {

  @Provides
  @Singleton
  static Logger provideLogger() {
    return Logger.getLogger("org.jfge");
  }
}
