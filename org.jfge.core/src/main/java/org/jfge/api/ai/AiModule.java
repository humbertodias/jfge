package org.jfge.api.ai;

import dagger.Binds;
import dagger.Module;

/** The Class AiModule. */
@Module
public abstract class AiModule {

  @Binds
  abstract AiControllerParser bindAiControllerParser(AiControllerParserImpl impl);
}
