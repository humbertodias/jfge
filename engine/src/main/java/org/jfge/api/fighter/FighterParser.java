package org.jfge.api.fighter;

import java.io.IOException;

/** A factory for creating Fighter objects. */
public interface FighterParser {

  Fighter parseFromXmlFile(String file) throws IOException;
}
