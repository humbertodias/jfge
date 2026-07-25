package org.jfge.games.sf2.fighter;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.fighter.Fighter;

@Module
public abstract class StreetFighter2FighterModule {

  @Provides
  @IntoMap
  @StringKey("ryu")
  static Fighter ryu(Ryu ryu) {
    return ryu.get();
  }

  @Provides
  @IntoMap
  @StringKey("blanka")
  static Fighter blanka(Blanka blanka) {
    return blanka.get();
  }
}
