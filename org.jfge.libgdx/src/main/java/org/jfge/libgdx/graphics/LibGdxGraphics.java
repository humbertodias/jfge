package org.jfge.libgdx.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

public final class LibGdxGraphics implements Graphics {

  private final SpriteBatch batch;
  private final ShapeRenderer shapeRenderer;
  private final int width;
  private final int height;
  private LibGdxColor currentColor;
  private LibGdxFont currentFont;
  private boolean inBatch = false;

  public LibGdxGraphics(SpriteBatch batch, ShapeRenderer shapeRenderer, int width, int height) {
    this.batch = batch;
    this.shapeRenderer = shapeRenderer;
    this.width = width;
    this.height = height;
    this.currentColor = new LibGdxColor(0, 0, 0, 255); // Black default
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setGraphicsColor(Color color) {
    if (color instanceof LibGdxColor) {
      this.currentColor = (LibGdxColor) color;
    }
  }

  @Override
  public void setGraphicsFont(Font font) {
    if (font instanceof LibGdxFont) {
      this.currentFont = (LibGdxFont) font;
    }
  }

  @Override
  public void drawRectangle(int x, int y, int width, int height) {
    ensureBatchEnded();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    if (currentColor != null) {
      shapeRenderer.setColor(currentColor.getGdxColor());
    }
    shapeRenderer.rect(x, y, width, height);
    shapeRenderer.end();
  }

  @Override
  public void drawFillRectangle(int x, int y, int width, int height) {
    ensureBatchEnded();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    if (currentColor != null) {
      shapeRenderer.setColor(currentColor.getGdxColor());
    }
    shapeRenderer.rect(x, y, width, height);
    shapeRenderer.end();
  }

  @Override
  public void drawString(int x, int y, String text) {
    if (text == null || text.isEmpty()) {
      return;
    }
    
    ensureBatchStarted();
    if (currentFont != null) {
      if (currentColor != null) {
        currentFont.getGdxFont().setColor(currentColor.getGdxColor());
      }
      currentFont.getGdxFont().draw(batch, text, x, y);
    }
  }

  @Override
  public void drawImage(int x, int y, Image image) {
    if (image == null) return;
    
    if (!(image instanceof LibGdxImage)) return;

    ensureBatchStarted();
    LibGdxImage gdxImage = (LibGdxImage) image;
    batch.draw(gdxImage.getRegion(), x, y);
  }

  public void beginBatch() {
    if (!inBatch) {
      batch.begin();
      inBatch = true;
    }
  }

  public void endBatch() {
    if (inBatch) {
      batch.end();
      inBatch = false;
    }
  }

  private void ensureBatchStarted() {
    if (!inBatch) {
      batch.begin();
      inBatch = true;
    }
  }

  private void ensureBatchEnded() {
    if (inBatch) {
      batch.end();
      inBatch = false;
    }
  }
}
