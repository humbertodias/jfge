package org.jfge.games.mk2.fighter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

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
   * @see com.google.inject.Provider#get()
   */
  @Override
  public Fighter get() {
    if (cyrax == null) {
      try {
        cyrax = fighterFactory.parseFromXmlFile("/org/jfge/games/mk2/fighter/cyrax/cyrax.xml");
      } catch (DOMException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      } catch (SAXException e) {
        e.printStackTrace();
      }
    }

    return cyrax;
  }
}
