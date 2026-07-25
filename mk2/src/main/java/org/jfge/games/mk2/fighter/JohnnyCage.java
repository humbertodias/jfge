package org.jfge.games.mk2.fighter;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;

/** The Class JohnnyCage. */
public final class JohnnyCage implements Provider<Fighter> {

  /** The kano. */
  private Fighter johnnyCage;

  /** The fighter factory. */
  private FighterParser fighterFactory;

  /**
   * Instantiates a new liu kang.
   *
   * @param fighterFactory the fighter factory
   */
  @Inject
  public JohnnyCage(FighterParser fighterFactory) {
    this.fighterFactory = fighterFactory;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  @Override
  public Fighter get() {
    if (johnnyCage == null) {
      try {
        johnnyCage =
            fighterFactory.parseFromXmlFile(
                "/org/jfge/games/mk2/fighter/johnnycage/johnnycage.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return johnnyCage;
  }
}
