package org.jfge.api.ai;

import com.badlogic.gdx.utils.XmlReader.Element;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import org.jfge.api.xml.XmlResources;

@Singleton
public final class AiControllerParserImpl implements AiControllerParser {

  private final AiControllerFactory aiControllerFactory;
  private final Logger logger;

  @Inject
  public AiControllerParserImpl(Logger logger, AiControllerFactory aiControllerFactory) {
    this.aiControllerFactory = aiControllerFactory;
    this.logger = logger;
  }

  @Override
  public AiController parseFromXmlFile(String file) throws IOException {
    Element root = XmlResources.parse(getClass(), file);
    HashMap<List<String>, String> transitions = new HashMap<>();

    for (Element transition : root.getChildrenByName("transition")) {
      List<String> tuple = new ArrayList<>();
      tuple.add(transition.getAttribute("dist"));
      tuple.add(transition.getAttribute("obsrvState"));
      tuple.add(transition.getAttribute("oppState"));
      transitions.put(tuple, transition.getAttribute("reaction"));
    }

    return aiControllerFactory.create(transitions);
  }
}
