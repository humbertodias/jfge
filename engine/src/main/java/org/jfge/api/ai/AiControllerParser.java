package org.jfge.api.ai;

import java.io.IOException;

/** The Class AiControllerParser. */
public interface AiControllerParser {

  AiController parseFromXmlFile(String file) throws IOException;
}
