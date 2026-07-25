package org.jfge.api.engine;

// TODO: Auto-generated Javadoc
/** The Interface Engine. */
public interface Engine extends Runnable, Updatable {

  /** Pause. */
  void pause();

  /** Resume. */
  void resume();

  /** Start. */
  void start();

  /**
   * Advance the engine when driven by an external loop (e.g. libGDX).
   *
   * @param deltaSeconds elapsed time since the previous frame
   */
  void tick(float deltaSeconds);

  /** Stop. */
  void stop();

  void addRenderable(Renderable renderable);

  void addUpdatable(Updatable updatable);

  void removeRenderable(Renderable renderable);

  void removeUpdatable(Updatable updatable);
}
