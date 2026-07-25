package org.jfge.libgdx.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public final class LibGdxKeyboardController1 extends LibGdxKeyboardController {

  @Inject
  public LibGdxKeyboardController1(
      @Named("keyboard.controller1.left") int left,
      @Named("keyboard.controller1.right") int right,
      @Named("keyboard.controller1.up") int up,
      @Named("keyboard.controller1.down") int down,
      @Named("keyboard.controller1.x") int x,
      @Named("keyboard.controller1.y") int y,
      @Named("keyboard.controller1.a") int a,
      @Named("keyboard.controller1.b") int b,
      @Named("keyboard.controller1.lr") int lr,
      @Named("keyboard.controller1.lb") int lb) {
    super(left, right, up, down, x, y, a, b, lr, lb);
  }
}
