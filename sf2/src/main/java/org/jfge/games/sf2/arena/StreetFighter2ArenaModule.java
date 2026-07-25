package org.jfge.games.sf2.arena;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.arena.Arena;

@Module
public abstract class StreetFighter2ArenaModule {

  @Provides
  @IntoMap
  @StringKey("ryuNight")
  static Arena ryuNight(RyuNight ryuNight) {
    return ryuNight.get();
  }

  @Provides
  @IntoMap
  @StringKey("blanka")
  static Arena blanka(Blanka blanka) {
    return blanka.get();
  }
}
