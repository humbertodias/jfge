package org.jfge.libgdx.controller;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.inject.Named;

@Singleton
public final class LibGdxKeyboardController2 extends LibGdxKeyboardController {

  @Inject
  public LibGdxKeyboardController2(
      @Named("keyboard.controller2.left") int left,
      @Named("keyboard.controller2.right") int right,
      @Named("keyboard.controller2.up") int up,
      @Named("keyboard.controller2.down") int down,
      @Named("keyboard.controller2.x") int x,
      @Named("keyboard.controller2.y") int y,
      @Named("keyboard.controller2.a") int a,
      @Named("keyboard.controller2.b") int b,
      @Named("keyboard.controller2.lr") int lr,
      @Named("keyboard.controller2.lb") int lb) {
    super(left, right, up, down, x, y, a, b, lr, lb);
  }
}
