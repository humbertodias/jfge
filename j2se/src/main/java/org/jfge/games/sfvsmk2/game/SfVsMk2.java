package org.jfge.games.sfvsmk2.game;

import org.jfge.api.game.Game;

public class SfVsMk2 {
  public static void main(String[] args) {
    SfVsMk2J2SeComponent component = DaggerSfVsMk2J2SeComponent.create();
    Game game = component.games().get("sfVsMk2");
    game.start();
  }
}
