package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import java.util.logging.Logger;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsProvider;

@Singleton
public final class LibGdxGraphicsProvider implements GraphicsProvider {

  private final Logger logger;
  private final int height;
  private final int width;
  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;
  private LibGdxGraphics graphics;

  @Inject
  public LibGdxGraphicsProvider(
      Logger logger, @Named("engine.height") int height, @Named("engine.width") int width) {
    this.height = height;
    this.width = width;
    this.logger = logger;

    logger.info("graphics provider resolution:" + width + " x " + height);
  }

  public void create() {
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    graphics = new LibGdxGraphics(batch, shapeRenderer, width, height);
    logger.info("LibGDX graphics provider initialized");
  }

  @Override
  public Graphics getGraphics() {
    return graphics;
  }

  @Override
  public void draw() {
    // Clear the screen
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  public void dispose() {
    if (batch != null) {
      batch.dispose();
    }
    if (shapeRenderer != null) {
      shapeRenderer.dispose();
    }
  }
}
