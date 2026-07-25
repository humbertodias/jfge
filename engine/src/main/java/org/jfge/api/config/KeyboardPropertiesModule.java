package org.jfge.api.config;

import dagger.Module;
import dagger.Provides;
import java.util.Properties;
import javax.inject.Named;

@Module
public abstract class KeyboardPropertiesModule {

  private static Properties props() {
    return PropertiesLoader.load("/org/jfge/config/controller/keyboard.properties");
  }

  @Provides
  @Named("keyboard.controller1.left")
  static int keyboardController1Left() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.left");
  }

  @Provides
  @Named("keyboard.controller1.right")
  static int keyboardController1Right() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.right");
  }

  @Provides
  @Named("keyboard.controller1.up")
  static int keyboardController1Up() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.up");
  }

  @Provides
  @Named("keyboard.controller1.down")
  static int keyboardController1Down() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.down");
  }

  @Provides
  @Named("keyboard.controller1.x")
  static int keyboardController1X() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.x");
  }

  @Provides
  @Named("keyboard.controller1.y")
  static int keyboardController1Y() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.y");
  }

  @Provides
  @Named("keyboard.controller1.a")
  static int keyboardController1A() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.a");
  }

  @Provides
  @Named("keyboard.controller1.b")
  static int keyboardController1B() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.b");
  }

  @Provides
  @Named("keyboard.controller1.lr")
  static int keyboardController1Lr() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.lr");
  }

  @Provides
  @Named("keyboard.controller1.lb")
  static int keyboardController1Lb() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.lb");
  }

  @Provides
  @Named("keyboard.controller1.start")
  static int keyboardController1Start() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.start");
  }

  @Provides
  @Named("keyboard.controller1.select")
  static int keyboardController1Select() {
    return PropertiesLoader.getInt(props(), "keyboard.controller1.select");
  }

  @Provides
  @Named("keyboard.controller2.left")
  static int keyboardController2Left() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.left");
  }

  @Provides
  @Named("keyboard.controller2.right")
  static int keyboardController2Right() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.right");
  }

  @Provides
  @Named("keyboard.controller2.up")
  static int keyboardController2Up() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.up");
  }

  @Provides
  @Named("keyboard.controller2.down")
  static int keyboardController2Down() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.down");
  }

  @Provides
  @Named("keyboard.controller2.x")
  static int keyboardController2X() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.x");
  }

  @Provides
  @Named("keyboard.controller2.y")
  static int keyboardController2Y() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.y");
  }

  @Provides
  @Named("keyboard.controller2.a")
  static int keyboardController2A() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.a");
  }

  @Provides
  @Named("keyboard.controller2.b")
  static int keyboardController2B() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.b");
  }

  @Provides
  @Named("keyboard.controller2.lr")
  static int keyboardController2Lr() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.lr");
  }

  @Provides
  @Named("keyboard.controller2.lb")
  static int keyboardController2Lb() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.lb");
  }

  @Provides
  @Named("keyboard.controller2.start")
  static int keyboardController2Start() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.start");
  }

  @Provides
  @Named("keyboard.controller2.select")
  static int keyboardController2Select() {
    return PropertiesLoader.getInt(props(), "keyboard.controller2.select");
  }
}
