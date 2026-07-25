package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jfge.spi.graphics.Image;

public final class LibGdxImage implements Image {

  private final Texture texture;
  private final TextureRegion region;
  private final String resourcePath;
  private final boolean flipX;
  private Image flipped;

  public LibGdxImage(Texture texture, String resourcePath) {
    this(texture, new TextureRegion(texture), resourcePath, false);
  }

  public LibGdxImage(Texture texture, TextureRegion region, String resourcePath, boolean flipX) {
    this.texture = texture;
    this.region = region;
    this.resourcePath = resourcePath;
    this.flipX = flipX;
  }

  public Texture getTexture() {
    return texture;
  }

  public TextureRegion getRegion() {
    return region;
  }

  public boolean isFlipX() {
    return flipX;
  }

  @Override
  public int getHeight() {
    return region.getRegionHeight();
  }

  @Override
  public int getWidth() {
    return region.getRegionWidth();
  }

  @Override
  public Image flip() {
    if (flipped == null) {
      flipped = new LibGdxImage(texture, new TextureRegion(region), resourcePath, !flipX);
    }
    return flipped;
  }

  @Override
  public Image rotate(int degree) {
    if (resourcePath == null) {
      return this;
    }

    Pixmap pixmap = new Pixmap(Gdx.files.classpath(toClasspathPath(resourcePath)));
    Pixmap rotated = rotatePixmap(pixmap, degree);
    pixmap.dispose();

    Texture rotatedTexture = new Texture(rotated);
    rotated.dispose();
    return new LibGdxImage(rotatedTexture, resourcePath);
  }

  private static String toClasspathPath(String resourcePath) {
    return resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
  }

  private static Pixmap rotatePixmap(Pixmap source, int degree) {
    int normalized = ((degree % 360) + 360) % 360;
    if (normalized == 0) {
      return copyPixmap(source);
    }

    int width = source.getWidth();
    int height = source.getHeight();
    int newWidth = (normalized == 90 || normalized == 270) ? height : width;
    int newHeight = (normalized == 90 || normalized == 270) ? width : height;
    Pixmap rotated = new Pixmap(newWidth, newHeight, source.getFormat());

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int color = source.getPixel(x, y);
        int targetX;
        int targetY;

        switch (normalized) {
          case 90:
            targetX = height - 1 - y;
            targetY = x;
            break;
          case 180:
            targetX = width - 1 - x;
            targetY = height - 1 - y;
            break;
          case 270:
            targetX = y;
            targetY = width - 1 - x;
            break;
          default:
            targetX = x;
            targetY = y;
            break;
        }

        rotated.drawPixel(targetX, targetY, color);
      }
    }

    return rotated;
  }

  private static Pixmap copyPixmap(Pixmap source) {
    Pixmap copy = new Pixmap(source.getWidth(), source.getHeight(), source.getFormat());
    copy.drawPixmap(source, 0, 0);
    return copy;
  }
}
