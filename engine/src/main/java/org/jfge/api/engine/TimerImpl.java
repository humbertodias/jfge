package org.jfge.api.engine;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.inject.Named;
import java.util.logging.Logger;

/** The Class TimerImpl. */
@Singleton
public final class TimerImpl implements org.jfge.api.engine.Timer {

  private final Logger logger;
  private final int noDelaysPerYield;
  private final int maxFrameSkips;
  private final int fps;
  private final Provider<Engine> engineProvider;
  private final boolean externalLoop;

  private long beforeTime;
  private long afterTime;
  private long overSleepTime;
  private int noDelays;
  private long excess;
  private long timeDiff;
  private long sleepTime;
  private long period;

  @Inject
  public TimerImpl(
      Provider<Engine> engineProvider,
      Logger logger,
      @Named("engine.fps") int fps,
      @Named("engine.nodelays") int noDelaysPerYield,
      @Named("engine.frameskip") int maxFrameSkips,
      @Named("engine.externalLoop") boolean externalLoop) {
    this.fps = fps;
    this.noDelaysPerYield = noDelaysPerYield;
    this.maxFrameSkips = maxFrameSkips;
    this.logger = logger;
    this.engineProvider = engineProvider;
    this.externalLoop = externalLoop;

    this.beforeTime = System.nanoTime();
    this.afterTime = this.beforeTime;
    this.period = 1000000000L / fps;

    logger.info("timer initialized with " + fps + " fps");
  }

  @Override
  public void measure() {
    beforeTime = System.nanoTime();
  }

  @Override
  public void sleep() {
    if (externalLoop) {
      return;
    }

    afterTime = System.nanoTime();
    timeDiff = afterTime - beforeTime;
    sleepTime = (period - timeDiff) - overSleepTime;

    if (sleepTime > 0) {
      try {
        Thread.sleep(sleepTime / 1000000L);
      } catch (InterruptedException ex) {
        // continue pacing
      }
      overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
    } else {
      excess -= sleepTime;
      overSleepTime = 0L;

      if (++noDelays >= noDelaysPerYield) {
        Thread.yield();
        noDelays = 0;
      }
    }

    int skips = 0;
    while (excess > period && skips < maxFrameSkips) {
      excess -= period;
      engineProvider.get().update();
      skips++;
    }
  }
}
