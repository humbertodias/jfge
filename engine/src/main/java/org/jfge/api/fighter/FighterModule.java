package org.jfge.api.fighter;

import dagger.Binds;
import dagger.Module;
import org.jfge.api.config.FighterPropertiesModule;

@Module(includes = FighterPropertiesModule.class)
public abstract class FighterModule {

  @Binds
  abstract FighterFactory bindFighterFactory(FighterFactoryImpl factory);

  @Binds
  abstract FighterParser bindFighterParser(FighterParserImpl parser);

  @Binds
  abstract InputQueue bindInputQueue(BufferedInputQueue inputQueue);
}
