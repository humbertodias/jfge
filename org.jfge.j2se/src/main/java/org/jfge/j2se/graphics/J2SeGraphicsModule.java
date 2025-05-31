package org.jfge.j2se.graphics;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import java.awt.event.KeyEvent; // For VK_ constants
import javax.inject.Named;
import javax.inject.Singleton; // J2SeKeyboardControllers are singletons
import org.jfge.j2se.controller.J2SeKeyboardController1;
import org.jfge.j2se.controller.J2SeKeyboardController2;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

/** The Class GraphicsModule. */
@Module
public abstract class J2SeGraphicsModule {

  @Binds
  abstract GraphicsProvider bindGraphicsProvider(J2SeGraphicsProvider impl);

  @Binds
  abstract GraphicsFactory bindGraphicsFactory(J2SeGraphicsFactory impl);

  // Bind J2SeKeyboardController1 and J2SeKeyboardController2 so they can be injected
  // into the @Provides methods below. They are Singleton so Dagger will manage that.
  @Binds
  abstract Controller bindJ2SeKeyboardController1AsController(J2SeKeyboardController1 impl);

  @Binds
  abstract Controller bindJ2SeKeyboardController2AsController(J2SeKeyboardController2 impl);


  @Provides
  @Named("keyboard.controller1")
  static Controller provideKeyboardController1(J2SeKeyboardController1 controller) {
    return controller;
  }

  @Provides
  @Named("keyboard.controller2")
  static Controller provideKeyboardController2(J2SeKeyboardController2 controller) {
    return controller;
  }

  @Provides
  @IntoMap
  @StringKey("keyboard.controller1")
  static Controller mapKeyboardController1(@Named("keyboard.controller1") Controller controller) {
    return controller;
  }

  @Provides
  @IntoMap
  @StringKey("keyboard.controller2")
  static Controller mapKeyboardController2(@Named("keyboard.controller2") Controller controller) {
    return controller;
  }

  // TODO: Provide actual key codes from properties or a config mechanism
  // Defaulting to VK_UNDEFINED or common keys for now.

  // Controller 1 Keybindings
  @Provides @Named("keyboard.controller1.left") static int provideC1Left() { return KeyEvent.VK_A; }
  @Provides @Named("keyboard.controller1.right") static int provideC1Right() { return KeyEvent.VK_D; }
  @Provides @Named("keyboard.controller1.up") static int provideC1Up() { return KeyEvent.VK_W; }
  @Provides @Named("keyboard.controller1.down") static int provideC1Down() { return KeyEvent.VK_S; }
  @Provides @Named("keyboard.controller1.x") static int provideC1X() { return KeyEvent.VK_J; }
  @Provides @Named("keyboard.controller1.y") static int provideC1Y() { return KeyEvent.VK_K; }
  @Provides @Named("keyboard.controller1.a") static int provideC1A() { return KeyEvent.VK_U; }
  @Provides @Named("keyboard.controller1.b") static int provideC1B() { return KeyEvent.VK_I; }
  @Provides @Named("keyboard.controller1.lr") static int provideC1Lr() { return KeyEvent.VK_L; }
  @Provides @Named("keyboard.controller1.lb") static int provideC1Lb() { return KeyEvent.VK_O; }
  @Provides @Named("keyboard.controller1.start") static int provideC1Start() { return KeyEvent.VK_ENTER; }
  @Provides @Named("keyboard.controller1.select") static int provideC1Select() { return KeyEvent.VK_SPACE; }

  // Controller 2 Keybindings
  @Provides @Named("keyboard.controller2.left") static int provideC2Left() { return KeyEvent.VK_LEFT; }
  @Provides @Named("keyboard.controller2.right") static int provideC2Right() { return KeyEvent.VK_RIGHT; }
  @Provides @Named("keyboard.controller2.up") static int provideC2Up() { return KeyEvent.VK_UP; }
  @Provides @Named("keyboard.controller2.down") static int provideC2Down() { return KeyEvent.VK_DOWN; }
  @Provides @Named("keyboard.controller2.x") static int provideC2X() { return KeyEvent.VK_NUMPAD4; }
  @Provides @Named("keyboard.controller2.y") static int provideC2Y() { return KeyEvent.VK_NUMPAD5; }
  @Provides @Named("keyboard.controller2.a") static int provideC2A() { return KeyEvent.VK_NUMPAD1; }
  @Provides @Named("keyboard.controller2.b") static int provideC2B() { return KeyEvent.VK_NUMPAD2; }
  @Provides @Named("keyboard.controller2.lr") static int provideC2Lr() { return KeyEvent.VK_NUMPAD6; }
  @Provides @Named("keyboard.controller2.lb") static int provideC2Lb() { return KeyEvent.VK_NUMPAD3; }
  @Provides @Named("keyboard.controller2.start") static int provideC2Start() { return KeyEvent.VK_F1; } // Example
  @Provides @Named("keyboard.controller2.select") static int provideC2Select() { return KeyEvent.VK_F2; } // Example
}
