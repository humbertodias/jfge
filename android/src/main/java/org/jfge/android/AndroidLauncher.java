package org.jfge.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import org.jfge.libgdx.JfgeApplication;
import org.jfge.libgdx.JfgeBootstrap;

/** Default Android launcher: Street Fighter vs Mortal Kombat 2. */
public class AndroidLauncher extends AndroidApplication {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useImmersiveMode(true);
    config.useAccelerometer = false;
    config.useCompass = false;

    initialize(new JfgeApplication(JfgeBootstrap.createComponent(), "sfVsMk2"), config);
  }
}
