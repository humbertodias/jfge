package org.jfge.api.engine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/** The Class EngineModule. */
@Module
public abstract class EngineModule {

  @Binds
  abstract Engine bindEngine(EngineImpl impl);

  @Binds
  abstract Timer bindTimer(TimerImpl impl);

  // TODO: Figure out how to best load properties in Dagger.
  // For now, providing default values.
  // Consider using a dedicated configuration object or reading properties at startup.

  @Provides
  @Named("engine.fps")
  static int provideEngineFps() {
    // Default value, ideally from properties file
    return 60;
  }

  @Provides
  @Named("engine.nodelays")
  static int provideEngineNoDelays() {
    // Default value, ideally from properties file
    return 16;
  }

  @Provides
  @Named("engine.frameskip")
  static int provideEngineFrameSkip() {
    // Default value, ideally from properties file
    return 5;
  }

  @Provides
  @Named("engine.width")
  static int provideEngineWidth() {
    // Default value, ideally from properties file
    // This was used in CollisionDetectorImpl, ensure it's a sensible default or configured.
    return 800;
  }
}
