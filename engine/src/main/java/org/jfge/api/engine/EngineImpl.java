package org.jfge.api.engine;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsProvider;

/** The Class EngineImpl. */
@Singleton
public final class EngineImpl implements org.jfge.api.engine.Engine {

  /** The logger. */
  private final Logger logger;

  /** The paused. */
  private boolean paused;

  /** The running. */
  private boolean running;

  /** The thread. */
  private Thread thread;

  /** The timer. */
  private org.jfge.api.engine.Timer timer;

  /** The graphics provider. */
  private GraphicsProvider graphicsProvider;

  /** The renderable. */
  private List<Renderable> renderables;

  /** The updateables. */
  private List<Updatable> updatables;

  /** When true, {@link #tick()} drives the loop instead of the engine thread. */
  private final boolean externalLoop;

  /**
   * Instantiates a new engine impl.
   *
   * @param graphicsProvider the graphics provider
   * @param timer the timer
   * @param logger the logger
   * @param externalLoop whether an external platform loop drives rendering
   */
  @Inject
  public EngineImpl(
      GraphicsProvider graphicsProvider,
      Timer timer,
      Logger logger,
      @Named("engine.externalLoop") boolean externalLoop) {
    this.graphicsProvider = graphicsProvider;
    this.timer = timer;
    this.logger = logger;
    this.externalLoop = externalLoop;
    this.thread = new Thread(this);

    this.renderables = new CopyOnWriteArrayList<Renderable>();
    this.updatables = new CopyOnWriteArrayList<Updatable>();
    this.paused = false;
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#update()
   */
  /** Update. */
  @Override
  public void update() {
    for (Updatable r : updatables) {
      r.update();
    }
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#render()
   */
  /** Render. */
  private void render() {
    Graphics graphics = graphicsProvider.getGraphics();
    /*
     * render game elements
     */
    for (Renderable r : renderables) {
      r.render(graphics);
    }
  }

  /**
   * Adds the renderable.
   *
   * @param renderable the renderable
   */
  @Override
  public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
  }

  /**
   * Adds the updatables.
   *
   * @param updatable the updatable
   */
  @Override
  public void addUpdatable(Updatable updatable) {
    this.updatables.add(updatable);
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#removeRenderable(org.jfge.engine.Renderable)
   */
  @Override
  public void removeRenderable(Renderable renderable) {
    this.renderables.remove(renderable);
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#removeUpdatable(org.jfge.engine.Updatable)
   */
  @Override
  public void removeUpdatable(Updatable updatable) {
    this.updatables.remove(updatable);
  }

  /* (non-Javadoc)
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {

    while (running) { // core main loop
      tickFrame();
    }
  }

  @Override
  public void tick() {
    if (!running || !externalLoop) return;

    tickFrame();
  }

  private void tickFrame() {
    timer.measure();

    // engine update
    if (!paused) update();

    // engine render
    render();

    // draw buffered graphics
    graphicsProvider.draw();

    // sleep intelligent
    timer.sleep();
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#pause()
   */
  @Override
  public void pause() {
    paused = true;
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#resume()
   */
  @Override
  public void resume() {
    paused = false;
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#start()
   */
  @Override
  public void start() {
    if (running) return;
    if (!externalLoop && thread.isAlive()) return;

    // starting main loop
    logger.info(
        externalLoop ? "core main loop started (external)" : "core main loop started");

    this.running = true;
    if (!externalLoop) {
      thread.start();
    }
  }

  /* (non-Javadoc)
   * @see org.jfge.engine.Engine#stop()
   */
  @Override
  public void stop() {
    this.running = false;
    while (thread.isAlive())
      ;
  }
}
