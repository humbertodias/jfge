package org.jfge.libgdx.graphics;

import com.badlogic.gdx.graphics.Color;

public final class LibGdxColor implements org.jfge.spi.graphics.Color {

  private final Color color;

  public LibGdxColor(int r, int g, int b, int a) {
    this.color = new Color(r / 255f, g / 255f, b / 255f, a / 255f);
  }

  public LibGdxColor(int argb) {
    // ARGB format: alpha is most significant byte
    int a = (argb >> 24) & 0xFF;
    int r = (argb >> 16) & 0xFF;
    int g = (argb >> 8) & 0xFF;
    int b = argb & 0xFF;
    this.color = new Color(r / 255f, g / 255f, b / 255f, a / 255f);
  }

  public Color getGdxColor() {
    return color;
  }
}
