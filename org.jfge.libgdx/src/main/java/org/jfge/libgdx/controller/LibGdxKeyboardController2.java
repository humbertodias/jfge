package org.jfge.libgdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import java.util.HashMap;
import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.controller.Controller;

@Singleton
public final class LibGdxKeyboardController2 extends InputAdapter implements Controller {

  private StateMachine stateMachine;
  private HashMap<Integer, String> key_pressed;
  private HashMap<Integer, String> key_released;

  private final int left;
  private final int right;
  private final int up;
  private final int down;
  private final int x;
  private final int y;
  private final int a;
  private final int b;
  private final int lr;
  private final int lb;
  private final int start;
  private final int select;

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
      @Named("keyboard.controller2.lb") int lb,
      @Named("keyboard.controller2.start") int start,
      @Named("keyboard.controller2.select") int select) {
    this.left = left;
    this.right = right;
    this.up = up;
    this.down = down;
    this.x = x;
    this.y = y;
    this.a = a;
    this.b = b;
    this.lr = lr;
    this.lb = lb;
    this.start = start;
    this.select = select;

    key_pressed = new HashMap<Integer, String>();
    key_released = new HashMap<Integer, String>();

    key_pressed.put(left, "leftPressed");
    key_pressed.put(right, "rightPressed");
    key_pressed.put(down, "downPressed");
    key_pressed.put(up, "upPressed");
    key_pressed.put(x, "xPressed");
    key_pressed.put(a, "aPressed");
    key_pressed.put(y, "yPressed");
    key_pressed.put(b, "bPressed");
    key_pressed.put(lr, "lrPressed");
    key_pressed.put(lb, "lbPressed");

    key_released.put(left, "leftReleased");
    key_released.put(right, "rightReleased");
    key_released.put(down, "downReleased");
    key_released.put(up, "upReleased");
    key_released.put(x, "xReleased");
    key_released.put(a, "aReleased");
    key_released.put(y, "yReleased");
    key_released.put(b, "bReleased");
    key_released.put(lr, "lrReleased");
    key_released.put(lb, "lbReleased");
  }

  @Override
  public boolean keyDown(int keycode) {
    if (stateMachine != null) {
      String event = key_pressed.get(keycode);
      if (event != null) {
        return stateMachine.handle(event);
      }
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (stateMachine != null) {
      String event = key_released.get(keycode);
      if (event != null) {
        return stateMachine.handle(event);
      }
    }
    return false;
  }

  @Override
  public void setStateMachine(StateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }
}
