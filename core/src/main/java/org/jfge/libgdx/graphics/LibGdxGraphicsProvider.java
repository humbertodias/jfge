package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import java.util.logging.Logger;
import org.jfge.spi.graphics.GraphicsProvider;

@Singleton
public final class LibGdxGraphicsProvider implements GraphicsProvider {

  private final Logger logger;
  private final int height;
  private final int width;

  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;
  private BitmapFont defaultFont;
  private LibGdxGraphics graphics;
  private Matrix4 projectionMatrix;
  private boolean initialized;

  @Inject
  public LibGdxGraphicsProvider(
      Logger logger, @Named("engine.height") int height, @Named("engine.width") int width) {
    this.height = height;
    this.width = width;
    this.logger = logger;
  }

  public void initialize() {
    if (initialized) return;

    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    defaultFont = new BitmapFont();
    defaultFont.getData().setScale(1f);

    projectionMatrix = new Matrix4();
    projectionMatrix.setToOrtho2D(0, 0, width, height);

    graphics =
        new LibGdxGraphics(batch, shapeRenderer, defaultFont, width, height, projectionMatrix);
    initialized = true;

    logger.info("libgdx graphics provider resolution:" + width + " x " + height);
  }

  @Override
  public org.jfge.spi.graphics.Graphics getGraphics() {
    if (!initialized) {
      throw new IllegalStateException("LibGdxGraphicsProvider.initialize() must be called first");
    }

    if (!batch.isDrawing()) {
      batch.begin();
      batch.setProjectionMatrix(projectionMatrix);
    }

    return graphics;
  }

  @Override
  public void draw() {
    if (!initialized) return;

    if (batch.isDrawing()) {
      batch.end();
    }
  }

  public void beginFrame() {
    if (!initialized) return;

    Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  public void dispose() {
    if (!initialized) return;

    if (batch != null) {
      batch.dispose();
    }
    if (shapeRenderer != null) {
      shapeRenderer.dispose();
    }
    if (defaultFont != null) {
      defaultFont.dispose();
    }
    initialized = false;
  }
}
