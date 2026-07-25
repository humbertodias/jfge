package org.jfge.api.engine;

import dagger.Binds;
import dagger.Module;
import org.jfge.api.config.EnginePropertiesModule;
import org.jfge.api.config.LoggingModule;
import org.jfge.api.config.MultibindingsModule;

@Module(includes = {EnginePropertiesModule.class, LoggingModule.class, MultibindingsModule.class})
public abstract class EngineModule {

  @Binds
  abstract Engine bindEngine(EngineImpl engine);

  @Binds
  abstract Timer bindTimer(TimerImpl timer);
}
