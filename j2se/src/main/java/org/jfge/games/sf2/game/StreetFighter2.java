package org.jfge.games.sf2.game;

import org.jfge.api.game.Game;

public class StreetFighter2 {
  public static void main(String[] args) {
    Sf2J2SeComponent component = DaggerSf2J2SeComponent.create();
    Game game = component.games().get("streetFighter2");
    game.start();
  }
}
