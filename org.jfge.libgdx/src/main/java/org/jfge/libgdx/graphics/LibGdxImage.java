package org.jfge.libgdx.graphics;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jfge.spi.graphics.Image;

public final class LibGdxImage implements Image {

  private final Texture texture;
  private final TextureRegion region;
  private Image flipped;

  public LibGdxImage(Texture texture) {
    this.texture = texture;
    this.region = new TextureRegion(texture);
  }

  private LibGdxImage(Texture texture, TextureRegion region) {
    this.texture = texture;
    this.region = region;
  }

  public Texture getTexture() {
    return texture;
  }

  public TextureRegion getRegion() {
    return region;
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
    if (this.flipped == null) {
      TextureRegion flippedRegion = new TextureRegion(region);
      flippedRegion.flip(true, false);
      this.flipped = new LibGdxImage(texture, flippedRegion);
    }
    return this.flipped;
  }

  @Override
  public Image rotate(int degree) {
    // TODO: Implement image rotation in libGDX
    // For proper implementation, we would need to:
    // 1. Create a Pixmap from the texture
    // 2. Apply rotation transformation
    // 3. Create a new texture from the rotated pixmap
    // Currently returning the same image as rotation is not used in the games
    return this;
  }

  public void dispose() {
    if (texture != null) {
      texture.dispose();
    }
  }
}
