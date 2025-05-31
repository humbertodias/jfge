package org.jfge.api.ai;

import dagger.assisted.AssistedFactory;
import java.util.List;
import java.util.Map;

/** A factory for creating AiController objects. */
@AssistedFactory
public interface AiControllerFactory {

  /**
   * Creates a new AiController object.
   *
   * @param transitions the transitions
   * @return the ai controller
   */
  AiController createAiController(Map<List<String>, String> transitions);
}
