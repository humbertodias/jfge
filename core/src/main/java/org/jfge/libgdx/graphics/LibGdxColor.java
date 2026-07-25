package org.jfge.libgdx.graphics;

public final class LibGdxColor implements org.jfge.spi.graphics.Color {

  private final int argb;

  public LibGdxColor(int argb) {
    this.argb = argb;
  }

  public int getArgb() {
    return argb;
  }

  public com.badlogic.gdx.graphics.Color toGdxColor() {
    float a = ((argb >> 24) & 0xff) / 255f;
    float r = ((argb >> 16) & 0xff) / 255f;
    float g = ((argb >> 8) & 0xff) / 255f;
    float b = (argb & 0xff) / 255f;
    return new com.badlogic.gdx.graphics.Color(r, g, b, a);
  }
}
