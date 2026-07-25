package org.jfge.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.api.config.ResourceLoader;
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
    String classpathPath = file.startsWith("/") ? file.substring(1) : file;

    try {
      if (Gdx.files != null) {
        FileHandle internal = Gdx.files.internal(classpathPath);
        if (internal.exists()) {
          return createImageFromBytes(internal.readBytes(), file);
        }
      }

      InputStream stream = openResourceStream(classpathPath, file);
      if (stream == null) {
        logger.info("can't load: " + file);
        return null;
      }

      byte[] bytes = readStreamBytes(stream);
      stream.close();
      return createImageFromBytes(bytes, file);
    } catch (Exception e) {
      logger.info("can't load: " + file + " (" + e.getMessage() + ")");
      return null;
    }
  }

  private Image createImageFromBytes(byte[] bytes, String file) {
    Pixmap pixmap = new Pixmap(bytes, 0, bytes.length);
    Texture texture = new Texture(pixmap);
    pixmap.dispose();
    texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    return new LibGdxImage(texture, file);
  }

  private byte[] readStreamBytes(InputStream stream) throws IOException {
    byte[] buffer = new byte[8192];
    int read;
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    while ((read = stream.read(buffer)) != -1) {
      out.write(buffer, 0, read);
    }
    return out.toByteArray();
  }

  private InputStream openResourceStream(String classpathPath, String originalPath) {
    InputStream stream = ResourceLoader.openStream(originalPath);
    if (stream != null) {
      return stream;
    }

    stream = ResourceLoader.openStream(classpathPath);
    if (stream != null) {
      return stream;
    }

    ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
    stream = openFromLoader(contextLoader, classpathPath, originalPath);
    if (stream != null) {
      return stream;
    }

    stream = openFromLoader(LibGdxGraphicsFactory.class.getClassLoader(), classpathPath, originalPath);
    if (stream != null) {
      return stream;
    }

    if (Gdx.files != null) {
      FileHandle internal = Gdx.files.internal(classpathPath);
      if (internal.exists()) {
        return internal.read();
      }

      FileHandle handle = Gdx.files.classpath(classpathPath);
      if (handle.exists()) {
        return handle.read();
      }
    }

    return null;
  }

  private InputStream openFromLoader(
      ClassLoader loader, String classpathPath, String originalPath) {
    if (loader == null) {
      return null;
    }

    InputStream stream = loader.getResourceAsStream(classpathPath);
    if (stream != null) {
      return stream;
    }

    return loader.getResourceAsStream(originalPath);
  }

  @Override
  public Color createColor(int color) {
    return new LibGdxColor(color);
  }

  @Override
  public Color createColor(String color) {
    return new LibGdxColor(Integer.parseInt(color));
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
