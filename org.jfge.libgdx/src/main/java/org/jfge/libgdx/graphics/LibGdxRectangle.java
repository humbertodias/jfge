package org.jfge.libgdx.graphics;

import com.badlogic.gdx.math.Rectangle;

public class LibGdxRectangle implements org.jfge.spi.graphics.Rectangle {

  private final Rectangle rectangle;

  public LibGdxRectangle(int x, int y, int width, int height) {
    this.rectangle = new Rectangle(x, y, width, height);
  }

  private LibGdxRectangle(Rectangle r) {
    this.rectangle = new Rectangle(r);
  }

  public Rectangle getGdxRectangle() {
    return rectangle;
  }

  @Override
  public org.jfge.spi.graphics.Rectangle rectIntersection(org.jfge.spi.graphics.Rectangle r) {
    if (r == null) return null;

    if (!(r instanceof LibGdxRectangle)) return null;

    Rectangle intersection = new Rectangle();
    if (this.rectangle.overlaps(((LibGdxRectangle) r).rectangle)) {
      // Calculate intersection
      float x1 = Math.max(rectangle.x, ((LibGdxRectangle) r).rectangle.x);
      float y1 = Math.max(rectangle.y, ((LibGdxRectangle) r).rectangle.y);
      float x2 =
          Math.min(
              rectangle.x + rectangle.width,
              ((LibGdxRectangle) r).rectangle.x + ((LibGdxRectangle) r).rectangle.width);
      float y2 =
          Math.min(
              rectangle.y + rectangle.height,
              ((LibGdxRectangle) r).rectangle.y + ((LibGdxRectangle) r).rectangle.height);

      intersection.set(x1, y1, x2 - x1, y2 - y1);
      return new LibGdxRectangle(intersection);
    }

    return null;
  }

  @Override
  public boolean rectIntersects(org.jfge.spi.graphics.Rectangle r) {
    if (r == null) return false;

    if (!(r instanceof LibGdxRectangle)) return false;

    return this.rectangle.overlaps(((LibGdxRectangle) r).rectangle);
  }

  @Override
  public int getRectHeight() {
    return (int) this.rectangle.height;
  }

  @Override
  public int getRectWidth() {
    return (int) this.rectangle.width;
  }

  @Override
  public int getRectX() {
    return (int) this.rectangle.x;
  }

  @Override
  public int getRectY() {
    return (int) this.rectangle.y;
  }
}
