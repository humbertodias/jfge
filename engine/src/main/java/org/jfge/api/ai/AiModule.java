package org.jfge.api.ai;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AiModule {

  @Binds
  abstract AiControllerParser bindAiControllerParser(AiControllerParserImpl parser);
}
