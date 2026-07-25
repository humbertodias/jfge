package org.jfge.libgdx.graphics;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.jfge.api.config.KeyboardPropertiesModule;
import org.jfge.libgdx.controller.LibGdxKeyboardController1;
import org.jfge.libgdx.controller.LibGdxKeyboardController2;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

@Module(includes = KeyboardPropertiesModule.class)
public abstract class LibGdxGraphicsModule {

  @Binds
  abstract GraphicsProvider bindGraphicsProvider(LibGdxGraphicsProvider provider);

  @Binds
  abstract GraphicsFactory bindGraphicsFactory(LibGdxGraphicsFactory factory);

  @Binds
  @Named("keyboard.controller1")
  abstract Controller bindKeyboardController1(LibGdxKeyboardController1 controller);

  @Binds
  @Named("keyboard.controller2")
  abstract Controller bindKeyboardController2(LibGdxKeyboardController2 controller);

  @Binds
  @IntoMap
  @StringKey("keyboard.controller1")
  abstract Controller bindKeyboardController1Map(LibGdxKeyboardController1 controller);

  @Binds
  @IntoMap
  @StringKey("keyboard.controller2")
  abstract Controller bindKeyboardController2Map(LibGdxKeyboardController2 controller);
}
