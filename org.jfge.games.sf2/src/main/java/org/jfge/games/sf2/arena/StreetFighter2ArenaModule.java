package org.jfge.games.sf2.arena;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Singleton; // Assuming RyuNight and Blanka providers should be singletons
import org.jfge.api.arena.Arena;

@Module
public class StreetFighter2ArenaModule {

  // RyuNight and Blanka are Providers<Arena> and have @Inject constructors.
  // Dagger can instantiate them. We'll assume they should be singletons
  // so the same provider instance is used, ensuring the arena itself is cached as per original logic.

  @Provides
  @Singleton // Ensure the provider itself is a singleton
  RyuNight provideRyuNightProvider(
      @javax.inject.Named("arenaRenderer.streetFighter2")
          org.jfge.spi.render.ArenaRenderer arenaRenderer,
      org.jfge.api.arena.ArenaFactory arenaFactory) {
    return new RyuNight(arenaRenderer, arenaFactory);
  }

  @Provides
  @Singleton // Ensure the provider itself is a singleton
  Blanka provideBlankaProvider(
      @javax.inject.Named("arenaRenderer.streetFighter2")
          org.jfge.spi.render.ArenaRenderer arenaRenderer,
      org.jfge.api.arena.ArenaFactory arenaFactory) {
    return new Blanka(arenaRenderer, arenaFactory);
  }

  @Provides
  @IntoMap
  @StringKey("ryuNight")
  Arena provideRyuNightArena(RyuNight ryuNightProvider) {
    return ryuNightProvider.get();
  }

  @Provides
  @IntoMap
  @StringKey("blanka")
  Arena provideBlankaArena(Blanka blankaProvider) {
    return blankaProvider.get();
  }
}
