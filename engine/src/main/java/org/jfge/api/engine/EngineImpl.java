package org.jfge.api.engine;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.inject.Named;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsProvider;

/** The Class EngineImpl. */
@Singleton
public final class EngineImpl implements org.jfge.api.engine.Engine {

  private static final float MAX_DELTA_SECONDS = 0.25f;

  private final Logger logger;
  private final boolean externalLoop;
  private final float frameTimeSeconds;
  private final int maxFrameSkips;

  private boolean paused;
  private boolean running;
  private Thread thread;
  private org.jfge.api.engine.Timer timer;
  private GraphicsProvider graphicsProvider;
  private List<Renderable> renderables;
  private List<Updatable> updatables;
  private float accumulator;

  @Inject
  public EngineImpl(
      GraphicsProvider graphicsProvider,
      Timer timer,
      Logger logger,
      @Named("engine.externalLoop") boolean externalLoop,
      @Named("engine.fps") int fps,
      @Named("engine.frameskip") int maxFrameSkips) {
    this.graphicsProvider = graphicsProvider;
    this.timer = timer;
    this.logger = logger;
    this.externalLoop = externalLoop;
    this.frameTimeSeconds = 1f / fps;
    this.maxFrameSkips = maxFrameSkips;
    this.thread = new Thread(this);

    this.renderables = new CopyOnWriteArrayList<Renderable>();
    this.updatables = new CopyOnWriteArrayList<Updatable>();
    this.paused = false;
  }

  @Override
  public void update() {
    for (Updatable r : updatables) {
      r.update();
    }
  }

  private void render() {
    Graphics graphics = graphicsProvider.getGraphics();
    for (Renderable r : renderables) {
      r.render(graphics);
    }
  }

  @Override
  public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
  }

  @Override
  public void addUpdatable(Updatable updatable) {
    this.updatables.add(updatable);
  }

  @Override
  public void removeRenderable(Renderable renderable) {
    this.renderables.remove(renderable);
  }

  @Override
  public void removeUpdatable(Updatable updatable) {
    this.updatables.remove(updatable);
  }

  @Override
  public void run() {
    while (running) {
      tickFrame();
    }
  }

  @Override
  public void tick(float deltaSeconds) {
    if (!running || !externalLoop) {
      return;
    }

    accumulator += Math.min(deltaSeconds, MAX_DELTA_SECONDS);

    int updates = 0;
    int maxUpdates = maxFrameSkips + 1;
    while (accumulator >= frameTimeSeconds && updates < maxUpdates) {
      accumulator -= frameTimeSeconds;
      if (!paused) {
        update();
      }
      updates++;
    }

    if (accumulator > frameTimeSeconds * 2f) {
      accumulator = frameTimeSeconds;
    }

    graphicsProvider.beginFrame();
    render();
    graphicsProvider.draw();
  }

  private void tickFrame() {
    timer.measure();

    if (!paused) {
      update();
    }

    render();
    graphicsProvider.draw();
    timer.sleep();
  }

  @Override
  public void pause() {
    paused = true;
  }

  @Override
  public void resume() {
    paused = false;
  }

  @Override
  public void start() {
    if (running) return;
    if (!externalLoop && thread.isAlive()) return;

    logger.info(
        externalLoop ? "core main loop started (external)" : "core main loop started");

    this.running = true;
    if (!externalLoop) {
      thread.start();
    }
  }

  @Override
  public void stop() {
    this.running = false;
    if (!externalLoop) {
      while (thread.isAlive()) {
        // wait for internal engine thread
      }
    }
  }
}
