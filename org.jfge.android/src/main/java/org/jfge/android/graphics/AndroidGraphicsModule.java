package org.jfge.android.graphics;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import javax.inject.Named;
import javax.inject.Singleton; // For AndroidKeyboardController if it's a singleton
import org.jfge.android.controller.AndroidKeyboardController;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

@Module
public class AndroidGraphicsModule {

  private final Activity activity;

  public AndroidGraphicsModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  Activity provideActivity() {
    return this.activity;
  }

  @Binds
  abstract GraphicsFactory bindGraphicsFactory(AndroidGraphicsFactory impl);

  @Binds
  abstract GraphicsProvider bindGraphicsProvider(AndroidGraphicsProvider impl);

  // Bind AndroidKeyboardController as Controller for general use
  // AndroidKeyboardController is already @Singleton
  @Binds
  abstract Controller bindAndroidKeyboardControllerAsController(AndroidKeyboardController impl);

  @Provides
  @Named("keyboard.android")
  Controller provideNamedAndroidController(AndroidKeyboardController controller) {
    // AndroidKeyboardController is @Singleton, Dagger provides the same instance.
    return controller;
  }

  @Provides
  @IntoSet
  Controller provideControllerToSet(AndroidKeyboardController controller) {
    // AndroidKeyboardController is @Singleton, Dagger provides the same instance.
    return controller;
  }
}
