package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.logging.Logger;
import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.graphics.Rectangle;

@Singleton
public final class LibGdxGraphicsFactory implements GraphicsFactory {

  private final Logger logger;

  @Inject
  public LibGdxGraphicsFactory(Logger logger) {
    this.logger = logger;
  }

  @Override
  public Image createImage(String file) throws IOException {
    try {
      // Remove leading slash if present for libGDX internal file handling
      String path = file.startsWith("/") ? file.substring(1) : file;
      
      FileHandle fileHandle = Gdx.files.internal(path);
      if (!fileHandle.exists()) {
        logger.warning("Image file not found: " + path);
        throw new IOException("Image file not found: " + path);
      }
      
      Texture texture = new Texture(fileHandle);
      return new LibGdxImage(texture);
    } catch (Exception e) {
      logger.severe("Failed to load image: " + file + " - " + e.getMessage());
      throw new IOException("Failed to load image: " + file, e);
    }
  }

  @Override
  public Color createColor(int color) {
    return new LibGdxColor(color);
  }

  @Override
  public Color createColor(String color) {
    try {
      return new LibGdxColor(Integer.parseInt(color));
    } catch (NumberFormatException e) {
      logger.warning("Invalid color string: " + color + ", defaulting to black");
      return new LibGdxColor(org.jfge.spi.graphics.Color.BLACK);
    }
  }

  @Override
  public Font createFont(String family, int style, int pointsize) {
    return new LibGdxFont(family, style, pointsize);
  }

  @Override
  public Rectangle createRectangle(int x, int y, int width, int height) {
    return new LibGdxRectangle(x, y, width, height);
  }
}
