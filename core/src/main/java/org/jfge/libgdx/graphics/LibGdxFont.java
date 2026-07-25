package org.jfge.libgdx.graphics;

public final class LibGdxFont implements org.jfge.spi.graphics.Font {

  private final String family;
  private final int style;
  private final int pointSize;

  public LibGdxFont(String family, int style, int pointSize) {
    this.family = family;
    this.style = style;
    this.pointSize = pointSize;
  }

  public String getFamily() {
    return family;
  }

  public int getStyle() {
    return style;
  }

  public int getPointSize() {
    return pointSize;
  }
}
