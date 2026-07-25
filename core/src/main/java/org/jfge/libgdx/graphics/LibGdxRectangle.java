package org.jfge.libgdx.graphics;

public final class LibGdxRectangle implements org.jfge.spi.graphics.Rectangle {

  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public LibGdxRectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getRectWidth() {
    return width;
  }

  @Override
  public int getRectHeight() {
    return height;
  }

  @Override
  public int getRectX() {
    return x;
  }

  @Override
  public int getRectY() {
    return y;
  }

  @Override
  public boolean rectIntersects(org.jfge.spi.graphics.Rectangle r) {
    if (r == null || !(r instanceof LibGdxRectangle)) return false;

    LibGdxRectangle other = (LibGdxRectangle) r;
    return x < other.x + other.width
        && x + width > other.x
        && y < other.y + other.height
        && y + height > other.y;
  }

  @Override
  public org.jfge.spi.graphics.Rectangle rectIntersection(org.jfge.spi.graphics.Rectangle r) {
    if (r == null || !(r instanceof LibGdxRectangle)) return null;

    LibGdxRectangle other = (LibGdxRectangle) r;
    int ix = Math.max(x, other.x);
    int iy = Math.max(y, other.y);
    int iw = Math.min(x + width, other.x + other.width) - ix;
    int ih = Math.min(y + height, other.y + other.height) - iy;

    if (iw <= 0 || ih <= 0) return null;

    return new LibGdxRectangle(ix, iy, iw, ih);
  }
}
