package org.jfge.api.fighter;

import com.badlogic.gdx.utils.XmlReader.Element;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.render.SpriteRenderer;
import org.jfge.api.sprite.Sprite;
import org.jfge.api.xml.XmlResources;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

@Singleton
public final class FighterParserImpl implements FighterParser {

  public final Logger logger;

  private final GraphicsFactory imageFactory;
  private final Map<String, Provider<SpritePhysics>> spritePhysics;
  private final Map<String, CollisionEffect> collisionEffects;
  private final Map<String, Projectile> projectiles;
  private final FighterFactory fighterFactory;

  @Inject
  public FighterParserImpl(
      Logger logger,
      GraphicsFactory imageFactory,
      Map<String, Provider<SpritePhysics>> spritePhysics,
      Map<String, CollisionEffect> collisionEffects,
      Provider<InputQueue> inputBufferQueueProvider,
      Provider<SpriteRenderer> spriteRendererProvider,
      FighterFactory fighterFactory,
      Map<String, Projectile> projectiles) {
    this.logger = logger;
    this.imageFactory = imageFactory;
    this.spritePhysics = spritePhysics;
    this.collisionEffects = collisionEffects;
    this.fighterFactory = fighterFactory;
    this.projectiles = projectiles;
  }

  private List<Image> parseFighterStateImages(Element state) throws IOException {
    List<Image> images = new ArrayList<>();

    for (Element image : state.getChildrenByName("image")) {
      images.add(imageFactory.createImage(image.getAttribute("src")));
    }

    return images;
  }

  @Override
  public Fighter parseFromXmlFile(String file) throws IOException {
    Element root = XmlResources.parse(getClass(), file);
    return parseFighter(root);
  }

  private Fighter parseFighter(Element root) throws IOException {
    List<FighterState> fighterStates = new ArrayList<>();

    String fighterName = root.getAttribute("name");
    String startState = root.getAttribute("startState");
    String endState = root.getAttribute("endState");
    String victoryState = root.getAttribute("victoryState");
    Image portrait = imageFactory.createImage(root.getAttribute("portrait"));

    for (Element state : root.getChildrenByName("state")) {
      fighterStates.add(parseFighterState(state, root));
    }

    return fighterFactory.createFighter(
        fighterName, portrait, fighterStates, startState, endState, victoryState);
  }

  private FighterState parseFighterState(Element state, Element root) throws IOException {
    String name = state.getAttribute("name");

    int damage = 0;
    String damageValue = state.getAttribute("damage", null);
    if (damageValue != null) {
      damage = Integer.parseInt(damageValue);
    }

    List<Image> images = parseFighterStateImages(state);

    int ticks = 1;
    String ticksValue = state.getAttribute("ticks", null);
    if (ticksValue != null && Integer.parseInt(ticksValue) > 0) {
      ticks = Integer.parseInt(ticksValue);
    }

    boolean loop = Boolean.parseBoolean(state.getAttribute("loop"));
    String nextState = state.getAttribute("nextState");

    boolean finalState = false;
    String finalStateValue = state.getAttribute("finalState", null);
    if (finalStateValue != null) {
      finalState = Boolean.parseBoolean(finalStateValue);
    }

    SpritePhysics physics = null;
    String move = state.getAttribute("move", null);
    if (move != null) {
      Provider<SpritePhysics> provider = spritePhysics.get(move);
      if (provider != null) {
        physics = provider.get();
      }
    }

    CollisionEffect effect = null;
    String effectName = state.getAttribute("effect", null);
    if (effectName != null) {
      effect = collisionEffects.get(effectName);
    }

    Projectile projectile = null;
    String projectileName = state.getAttribute("projectile", null);
    if (projectileName != null) {
      projectile = projectiles.get(projectileName);
    }

    HashMap<List<String>, String> stateTransitions = parseFighterStateTransitions(name, root);

    return fighterFactory.createFighterState(
        name,
        damage,
        images,
        ticks,
        loop,
        nextState,
        finalState,
        stateTransitions,
        physics,
        effect,
        projectile);
  }

  private HashMap<List<String>, String> parseFighterStateTransitions(String state, Element root) {
    HashMap<List<String>, String> transitions = new HashMap<>();

    for (Element transition : root.getChildrenByName("transition")) {
      if (!state.equals(transition.getAttribute("state"))) {
        continue;
      }

      List<String> list = new ArrayList<>();
      String[] events = transition.getAttribute("event").split(",");
      String nextState = transition.getAttribute("nextState");

      String direction = transition.getAttribute("direction", null);
      if (direction != null) {
        if (direction.equals("left")) {
          list.add(Integer.toString(Sprite.LEFT));
        } else {
          list.add(Integer.toString(Sprite.RIGHT));
        }
      }

      for (String event : events) {
        list.add(event.trim());
      }

      transitions.put(list, nextState);
    }

    return transitions;
  }
}
