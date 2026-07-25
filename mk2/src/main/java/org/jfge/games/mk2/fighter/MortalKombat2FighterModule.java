package org.jfge.games.mk2.fighter;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.fighter.Fighter;

@Module
public abstract class MortalKombat2FighterModule {

  @Provides
  @IntoMap
  @StringKey("kano")
  static Fighter kano(Kano kano) {
    return kano.get();
  }

  @Provides
  @IntoMap
  @StringKey("liuKang")
  static Fighter liuKang(LiuKang liuKang) {
    return liuKang.get();
  }

  @Provides
  @IntoMap
  @StringKey("cyrax")
  static Fighter cyrax(Cyrax cyrax) {
    return cyrax.get();
  }

  @Provides
  @IntoMap
  @StringKey("johnnyCage")
  static Fighter johnnyCage(JohnnyCage johnnyCage) {
    return johnnyCage.get();
  }
}
