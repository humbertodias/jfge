package org.jfge.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.inject.Module;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.jfge.api.engine.EngineModule;
import org.jfge.libgdx.JfgeApplication;
import org.jfge.libgdx.JfgeGameStarter;

public final class DesktopLauncher {

  private DesktopLauncher() {}

  public static void launch(Module[] modules, String gameKey) {
    Properties engineProperties = loadEngineProperties();
    int width = Integer.parseInt(engineProperties.getProperty("engine.width", "480"));
    int height = Integer.parseInt(engineProperties.getProperty("engine.height", "272"));

    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setWindowedMode(width, height);
    config.setTitle("Java Fighting Game Engine");
    config.useVsync(true);

    new Lwjgl3Application(
        new JfgeApplication(modules, JfgeGameStarter.forGameKey(gameKey)), config);
  }

  private static Properties loadEngineProperties() {
    Properties properties = new Properties();
    try (InputStream stream =
        EngineModule.class.getResourceAsStream("/org/jfge/config/engine/engine.properties")) {
      if (stream != null) {
        properties.load(stream);
      }
    } catch (IOException ignored) {
      // use defaults configured above
    }
    return properties;
  }
}
