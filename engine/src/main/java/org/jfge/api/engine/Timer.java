package org.jfge.api.engine;

/** The Interface Timer. */
public interface Timer {

  /** Measure. */
  void measure();

  /** Sleep until the next frame when driven by the internal engine thread. */
  void sleep();
}
