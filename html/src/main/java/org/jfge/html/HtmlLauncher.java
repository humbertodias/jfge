package org.jfge.html;

import com.github.xpenatan.gdx.teavm.backends.web.WebApplication;
import com.github.xpenatan.gdx.teavm.backends.web.WebApplicationConfiguration;
import org.jfge.libgdx.JfgeApplication;

public final class HtmlLauncher {

  private HtmlLauncher() {}

  public static void launch(String gameKey) {
    WebApplicationConfiguration config = new WebApplicationConfiguration();
    config.width = 480;
    config.height = 272;
    config.showDownloadLogs = true;
    config.useGL30 = false;

    new WebApplication(new JfgeApplication(gameKey), config);
  }
}
