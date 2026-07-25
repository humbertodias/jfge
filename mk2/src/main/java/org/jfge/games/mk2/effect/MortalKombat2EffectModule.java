package org.jfge.games.mk2.effect;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.effect.CollisionEffect;

@Module
public abstract class MortalKombat2EffectModule {

  @Provides
  @IntoMap
  @StringKey("FightAnimation")
  static ArenaEffect fightAnimation(FightAnimation fightAnimation) {
    return fightAnimation.get();
  }

  @Provides
  @IntoMap
  @StringKey("HighHitBlood")
  static CollisionEffect highHitBlood(HighHitBlood highHitBlood) {
    return highHitBlood.get();
  }

  @Provides
  @IntoMap
  @StringKey("LowHitBlood")
  static CollisionEffect lowHitBlood(LowHitBlood lowHitBlood) {
    return lowHitBlood.get();
  }

  @Provides
  @IntoMap
  @StringKey("FallDownBlood")
  static CollisionEffect fallDownBlood(FallDownBlood fallDownBlood) {
    return fallDownBlood.get();
  }
}
