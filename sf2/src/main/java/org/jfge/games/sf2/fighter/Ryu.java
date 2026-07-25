package org.jfge.games.sf2.fighter;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;

/** The Class Ryu. */
@Singleton
public class Ryu implements Provider<Fighter> {

  /** The ryu. */
  private Fighter ryu;

  /** The fighter factory. */
  private FighterParser fighterFactory;

  /**
   * Instantiates a new ryu.
   *
   * @param fighterFactory the fighter factory
   */
  @Inject
  public Ryu(FighterParser fighterFactory) {
    this.fighterFactory = fighterFactory;
  }

  /* (non-Javadoc)
   * @see javax.inject.Provider#get()
   */
  @Override
  public Fighter get() {
    if (ryu == null) {
      try {
        ryu = fighterFactory.parseFromXmlFile("/org/jfge/games/sf2/fighter/ryu/ryu.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return ryu;
  }
}
