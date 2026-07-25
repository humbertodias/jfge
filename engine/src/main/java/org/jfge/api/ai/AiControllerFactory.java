package org.jfge.api.ai;

import dagger.assisted.AssistedFactory;
import java.util.List;
import java.util.Map;

/** A factory for creating AiController objects. */
@AssistedFactory
public interface AiControllerFactory {

  AiControllerImpl create(Map<List<String>, String> transitions);
}
