package org.jfge.libgdx.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.IntSet;
import com.google.inject.name.Named;
import java.util.HashMap;
import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.controller.Controller;

abstract class LibGdxKeyboardController implements Controller {

  private final HashMap<Integer, String> keyPressed = new HashMap<>();
  private final HashMap<Integer, String> keyReleased = new HashMap<>();
  private final IntSet activeKeys = new IntSet();
  private StateMachine stateMachine;

  protected LibGdxKeyboardController(
      @Named("left") int left,
      @Named("right") int right,
      @Named("up") int up,
      @Named("down") int down,
      @Named("x") int x,
      @Named("y") int y,
      @Named("a") int a,
      @Named("b") int b,
      @Named("lr") int lr,
      @Named("lb") int lb) {
    keyPressed.put(left, "leftPressed");
    keyPressed.put(right, "rightPressed");
    keyPressed.put(down, "downPressed");
    keyPressed.put(up, "upPressed");
    keyPressed.put(x, "xPressed");
    keyPressed.put(a, "aPressed");
    keyPressed.put(y, "yPressed");
    keyPressed.put(b, "bPressed");
    keyPressed.put(lr, "lrPressed");
    keyPressed.put(lb, "lbPressed");

    keyReleased.put(left, "leftReleased");
    keyReleased.put(right, "rightReleased");
    keyReleased.put(down, "downReleased");
    keyReleased.put(up, "upReleased");
    keyReleased.put(x, "xReleased");
    keyReleased.put(a, "aReleased");
    keyReleased.put(y, "yReleased");
    keyReleased.put(b, "bReleased");
    keyReleased.put(lr, "lrReleased");
    keyReleased.put(lb, "lbReleased");
  }

  @Override
  public void setStateMachine(StateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  public void update() {
    if (stateMachine == null || Gdx.input == null) return;

    for (HashMap.Entry<Integer, String> entry : keyPressed.entrySet()) {
      int gdxKey = KeyMapper.toGdxKey(entry.getKey());
      if (Gdx.input.isKeyJustPressed(gdxKey)) {
        stateMachine.handle(entry.getValue());
        activeKeys.add(gdxKey);
      }
    }

    IntSet keysToRelease = new IntSet();
    for (IntSet.IntSetIterator it = activeKeys.iterator(); it.hasNext; ) {
      int gdxKey = it.next();
      if (!Gdx.input.isKeyPressed(gdxKey)) {
        keysToRelease.add(gdxKey);
      }
    }

    for (IntSet.IntSetIterator it = keysToRelease.iterator(); it.hasNext; ) {
      int gdxKey = it.next();
      activeKeys.remove(gdxKey);
      for (HashMap.Entry<Integer, String> entry : keyReleased.entrySet()) {
        if (KeyMapper.toGdxKey(entry.getKey()) == gdxKey) {
          stateMachine.handle(entry.getValue());
          break;
        }
      }
    }
  }
}
