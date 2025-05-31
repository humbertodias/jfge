package org.jfge.games.sf2.fighter;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Singleton; // For Ryu provider
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser; // Ryu and Blanka need this

@Module
public class StreetFighter2FighterModule {

  // Ryu is @Singleton, Blanka is not. Dagger will respect these.
  // The @Provides methods for Ryu and Blanka themselves are not strictly needed
  // if they have @Inject constructors and Dagger can find their dependencies (FighterParser).
  // However, explicitly providing them makes the module clearer.

  @Provides
  @Singleton // Ryu provider is a singleton
  Ryu provideRyuProvider(FighterParser fighterParser) {
    return new Ryu(fighterParser);
  }

  @Provides
  Blanka provideBlankaProvider(FighterParser fighterParser) {
    // Blanka provider is not a singleton
    return new Blanka(fighterParser);
  }

  @Provides
  @IntoMap
  @StringKey("ryu")
  Fighter provideRyuFighter(Ryu ryuProvider) {
    // Fighter instance from Ryu provider will be cached within Ryu provider
    return ryuProvider.get();
  }

  @Provides
  @IntoMap
  @StringKey("blanka")
  Fighter provideBlankaFighter(Blanka blankaProvider) {
    // Fighter instance from Blanka provider will be cached within Blanka provider
    return blankaProvider.get();
  }
}
