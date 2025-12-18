package org.jfge.libgdx.graphics;

import com.badlogic.gdx.graphics.Color;

public final class LibGdxColor implements org.jfge.spi.graphics.Color {

  private final Color color;

  public LibGdxColor(int r, int g, int b, int a) {
    this.color = new Color(r / 255f, g / 255f, b / 255f, a / 255f);
  }

  public LibGdxColor(int rgba) {
    int r = (rgba >> 16) & 0xFF;
    int g = (rgba >> 8) & 0xFF;
    int b = rgba & 0xFF;
    int a = (rgba >> 24) & 0xFF;
    this.color = new Color(r / 255f, g / 255f, b / 255f, a / 255f);
  }

  public Color getGdxColor() {
    return color;
  }
}
