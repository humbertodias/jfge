package org.jfge.games.mk2.game;

import org.jfge.api.game.Game;

public class MortalKombat2 {

  public static void main(String[] args) {
    Mk2J2SeComponent component = DaggerMk2J2SeComponent.create();
    Game game = component.games().get("mortalKombat2");
    game.start();
  }
}
