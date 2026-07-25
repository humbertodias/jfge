package org.jfge.j2se.graphics;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import org.jfge.api.config.KeyboardPropertiesModule;
import org.jfge.j2se.controller.J2SeKeyboardController1;
import org.jfge.j2se.controller.J2SeKeyboardController2;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

@Module(includes = KeyboardPropertiesModule.class)
public abstract class J2SeGraphicsModule {

  @Binds
  abstract GraphicsProvider bindGraphicsProvider(J2SeGraphicsProvider provider);

  @Binds
  abstract GraphicsFactory bindGraphicsFactory(J2SeGraphicsFactory factory);

  @Binds
  @Named("keyboard.controller1")
  abstract Controller bindKeyboardController1(J2SeKeyboardController1 controller);

  @Binds
  @Named("keyboard.controller2")
  abstract Controller bindKeyboardController2(J2SeKeyboardController2 controller);

  @Binds
  @IntoMap
  @StringKey("keyboard.controller1")
  abstract Controller bindKeyboardController1Map(J2SeKeyboardController1 controller);

  @Binds
  @IntoMap
  @StringKey("keyboard.controller2")
  abstract Controller bindKeyboardController2Map(J2SeKeyboardController2 controller);
}
