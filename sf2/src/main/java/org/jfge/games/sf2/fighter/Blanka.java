package org.jfge.games.sf2.fighter;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;

/** The Class Blanka. */
public class Blanka implements Provider<Fighter> {

  /** The blanka. */
  private Fighter blanka;

  /** The fighter factory. */
  private FighterParser fighterFactory;

  /**
   * Instantiates a new blanka.
   *
   * @param fighterFactory the fighter factory
   */
  @Inject
  public Blanka(FighterParser fighterFactory) {
    this.fighterFactory = fighterFactory;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  @Override
  public Fighter get() {
    if (blanka == null) {
      try {
        blanka = fighterFactory.parseFromXmlFile("/org/jfge/games/sf2/fighter/blanka/blanka.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return blanka;
  }
}
