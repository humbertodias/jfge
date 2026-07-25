package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.inject.Named;
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
  private FrameBuffer frameBuffer;
  private TextureRegion frameRegion;
  private Matrix4 projectionMatrix;
  private Matrix4 screenMatrix;
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

    screenMatrix = new Matrix4();

    frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
    frameRegion = new TextureRegion(frameBuffer.getColorBufferTexture());
    frameRegion.flip(false, true);

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
  public void beginFrame() {
    if (!initialized) return;

    frameBuffer.begin();
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void draw() {
    if (!initialized) return;

    graphics.flushShapes();
    if (batch.isDrawing()) {
      batch.end();
    }

    frameBuffer.end();

    Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    screenMatrix.setToOrtho2D(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    batch.begin();
    batch.setProjectionMatrix(screenMatrix);
    batch.setColor(1f, 1f, 1f, 1f);
    batch.draw(
        frameRegion, 0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    batch.end();
  }

  public void dispose() {
    if (!initialized) return;

    if (frameBuffer != null) {
      frameBuffer.dispose();
    }
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
