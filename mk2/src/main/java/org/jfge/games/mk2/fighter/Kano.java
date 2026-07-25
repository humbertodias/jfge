package org.jfge.games.mk2.fighter;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;

/** The Class Kano. */
@Singleton
public final class Kano implements Provider<Fighter> {

  /** The kano. */
  private Fighter kano;

  /** The fighter factory. */
  private FighterParser fighterFactory;

  /**
   * Instantiates a new liu kang.
   *
   * @param fighterFactory the fighter factory
   */
  @Inject
  public Kano(FighterParser fighterFactory) {
    this.fighterFactory = fighterFactory;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  @Override
  public Fighter get() {
    if (kano == null) {
      try {
        kano = fighterFactory.parseFromXmlFile("/org/jfge/games/mk2/fighter/kano/kano.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return kano;
  }
}
