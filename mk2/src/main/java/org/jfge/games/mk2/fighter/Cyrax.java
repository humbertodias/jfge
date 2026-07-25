package org.jfge.games.mk2.fighter;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;

/** The Class Cyrax. */
public final class Cyrax implements Provider<Fighter> {

  /** The cyrax. */
  private Fighter cyrax;

  /** The fighter factory. */
  private FighterParser fighterFactory;

  /**
   * Instantiates a new liu kang.
   *
   * @param fighterFactory the fighter factory
   */
  @Inject
  public Cyrax(FighterParser fighterFactory) {
    this.fighterFactory = fighterFactory;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  @Override
  public Fighter get() {
    if (cyrax == null) {
      try {
        cyrax = fighterFactory.parseFromXmlFile("/org/jfge/games/mk2/fighter/cyrax/cyrax.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return cyrax;
  }
}
