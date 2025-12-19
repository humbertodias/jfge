package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
  private OrthographicCamera camera;

  @Inject
  public LibGdxGraphicsProvider(
      Logger logger, @Named("engine.height") int height, @Named("engine.width") int width) {
    this.height = height;
    this.width = width;
    this.logger = logger;

    logger.info("graphics provider resolution:" + width + " x " + height);
  }

  public void create() {
    // Set up camera with Y-down coordinate system (0,0 at top-left)
    camera = new OrthographicCamera();
    camera.setToOrtho(true, width, height); // true = Y-down
    camera.update(); // Calculate matrices once
    
    batch = new SpriteBatch();
    batch.setProjectionMatrix(camera.combined);
    
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(camera.combined);
    
    graphics = new LibGdxGraphics(batch, shapeRenderer, width, height);
    logger.info("LibGDX graphics provider initialized with Y-down coordinate system");
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
    // Projection matrices are set once in create() since camera is static
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
