package org.jfge.libgdx.graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class LibGdxFont implements org.jfge.spi.graphics.Font {

  private final BitmapFont font;
  private final String family;
  private final int style;
  private final int pointsize;

  public LibGdxFont(String family, int style, int pointsize) {
    this.family = family;
    this.style = style;
    this.pointsize = pointsize;
    // libGDX uses BitmapFont, for simplicity we use the default font
    this.font = new BitmapFont();
    // Scale font based on pointsize (default is around 15)
    float scale = pointsize / 15f;
    this.font.getData().setScale(scale);
  }

  public BitmapFont getGdxFont() {
    return font;
  }

  public void dispose() {
    font.dispose();
  }
}
