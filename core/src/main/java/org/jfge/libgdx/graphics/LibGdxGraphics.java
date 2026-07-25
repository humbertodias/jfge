package org.jfge.libgdx.graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

public final class LibGdxGraphics implements Graphics {

  private final SpriteBatch batch;
  private final ShapeRenderer shapeRenderer;
  private final BitmapFont defaultFont;
  private final Matrix4 projectionMatrix;
  private final int width;
  private final int height;

  private com.badlogic.gdx.graphics.Color drawColor =
      com.badlogic.gdx.graphics.Color.WHITE.cpy();
  private BitmapFont currentFont;
  private ShapeRenderer.ShapeType pendingShapeType;

  public LibGdxGraphics(
      SpriteBatch batch,
      ShapeRenderer shapeRenderer,
      BitmapFont defaultFont,
      int width,
      int height,
      Matrix4 projectionMatrix) {
    this.batch = batch;
    this.shapeRenderer = shapeRenderer;
    this.defaultFont = defaultFont;
    this.width = width;
    this.height = height;
    this.projectionMatrix = projectionMatrix;
    this.currentFont = defaultFont;
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
      drawColor = ((LibGdxColor) color).toGdxColor();
    }
  }

  @Override
  public void setGraphicsFont(Font font) {
    if (!(font instanceof LibGdxFont)) return;

    LibGdxFont libGdxFont = (LibGdxFont) font;
    currentFont = defaultFont;
    currentFont.setColor(drawColor);
    currentFont.getData().setScale(libGdxFont.getPointSize() / 13f);
  }

  @Override
  public void drawRectangle(int x, int y, int width, int height) {
    drawShape(ShapeRenderer.ShapeType.Line, x, y, width, height);
  }

  @Override
  public void drawFillRectangle(int x, int y, int width, int height) {
    drawShape(ShapeRenderer.ShapeType.Filled, x, y, width, height);
  }

  @Override
  public void drawString(int x, int y, String text) {
    if (text == null) return;

    flushShapes();
    currentFont.setColor(drawColor);
    currentFont.draw(batch, text, x, toBaselineY(y));
  }

  @Override
  public void drawImage(int x, int y, Image image) {
    if (!(image instanceof LibGdxImage)) return;

    flushShapes();
    LibGdxImage libGdxImage = (LibGdxImage) image;
    TextureRegion region = libGdxImage.getRegion();
    int imageWidth = region.getRegionWidth();
    int imageHeight = region.getRegionHeight();
    float drawY = toBottomLeftY(y, imageHeight);

    batch.setColor(com.badlogic.gdx.graphics.Color.WHITE);
    if (libGdxImage.isFlipX()) {
      batch.draw(region, x + imageWidth, drawY, 0, 0, imageWidth, imageHeight, -1, 1, 0);
    } else {
      batch.draw(region, x, drawY);
    }
  }

  void flushShapes() {
    if (pendingShapeType == null) return;

    shapeRenderer.end();
    pendingShapeType = null;
    beginBatchIfNeeded();
  }

  private void drawShape(ShapeRenderer.ShapeType type, int x, int y, int width, int height) {
    if (pendingShapeType != null && pendingShapeType != type) {
      flushShapes();
    }

    endBatchIfNeeded();

    if (pendingShapeType == null) {
      shapeRenderer.setProjectionMatrix(projectionMatrix);
      shapeRenderer.begin(type);
      pendingShapeType = type;
    }

    shapeRenderer.setColor(drawColor);
    shapeRenderer.rect(x, toBottomLeftY(y, height), width, height);
  }

  private float toBaselineY(int y) {
    float scale = currentFont.getData().scaleY;
    return height - y + (10f * scale);
  }

  private float toBottomLeftY(int topY, int rectHeight) {
    return height - topY - rectHeight;
  }

  private void endBatchIfNeeded() {
    if (batch.isDrawing()) {
      batch.end();
    }
  }

  private void beginBatchIfNeeded() {
    if (!batch.isDrawing()) {
      batch.begin();
      batch.setProjectionMatrix(projectionMatrix);
    }
  }
}
