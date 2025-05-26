package org.jfge.games.sf2.game;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.jfge.api.game.Game;

public class StreetFighter2GameModule extends AbstractModule {

  @Override
  protected void configure() {
    MapBinder<String, Game> gameBinder = MapBinder.newMapBinder(binder(), String.class, Game.class);

    gameBinder.addBinding("streetFighter2").toProvider(StreetFighter2Game.class);
  }
}
