package org.jfge.api.engine;

// TODO: Auto-generated Javadoc
/** The Interface Engine. */
public interface Engine extends Runnable, Updatable {

  /** Pause. */
  public void pause();

  /** Resume. */
  public void resume();

  /** Start. */
  public void start();

  /** Advance one frame when driven by an external loop (e.g. libGDX). */
  public void tick();

  /** Stop. */
  public void stop();

  /**
   * Adds the renderable.
   *
   * @param renderable the renderable
   */
  public void addRenderable(Renderable renderable);

  /**
   * Adds the updatables.
   *
   * @param updatable the updatable
   */
  public void addUpdatable(Updatable updatable);

  /**
   * Removes the renderable.
   *
   * @param renderable the renderable
   */
  public void removeRenderable(Renderable renderable);

  /**
   * Removes the updatable.
   *
   * @param updatable the updatable
   */
  public void removeUpdatable(Updatable updatable);
}
