package org.jfge.spi.graphics;

/** The Interface GraphicsProvider. */
public interface GraphicsProvider {

  /** Prepares a new frame for off-screen rendering. */
  default void beginFrame() {}

  /**
   * Gets the graphics.
   *
   * @return the graphics
   */
  public Graphics getGraphics();

  /** Presents the completed frame to the display. */
  public void draw();
}
