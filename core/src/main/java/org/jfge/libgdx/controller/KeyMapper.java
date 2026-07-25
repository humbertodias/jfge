package org.jfge.libgdx.controller;

import com.badlogic.gdx.Input;
import java.awt.event.KeyEvent;

final class KeyMapper {

  private KeyMapper() {}

  static int toGdxKey(int awtKeyCode) {
    switch (awtKeyCode) {
      case KeyEvent.VK_LEFT:
        return Input.Keys.LEFT;
      case KeyEvent.VK_RIGHT:
        return Input.Keys.RIGHT;
      case KeyEvent.VK_UP:
        return Input.Keys.UP;
      case KeyEvent.VK_DOWN:
        return Input.Keys.DOWN;
      case KeyEvent.VK_ENTER:
        return Input.Keys.ENTER;
      case KeyEvent.VK_ESCAPE:
        return Input.Keys.ESCAPE;
      case KeyEvent.VK_DELETE:
        return Input.Keys.FORWARD_DEL;
      case KeyEvent.VK_SPACE:
        return Input.Keys.SPACE;
      default:
        return awtKeyCode;
    }
  }
}
