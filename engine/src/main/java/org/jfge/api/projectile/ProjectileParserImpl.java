package org.jfge.api.projectile;

import com.badlogic.gdx.utils.XmlReader.Element;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.jfge.api.xml.XmlResources;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

@Singleton
final class ProjectileParserImpl implements ProjectileParser {

  private final ProjectileFactory projectileFactory;
  private final GraphicsFactory imageFactory;
  private final Map<String, Provider<SpritePhysics>> spritePhysics;
  private final Logger logger;

  @Inject
  public ProjectileParserImpl(
      Logger logger,
      ProjectileFactory particleFactory,
      GraphicsFactory imageFactory,
      Map<String, Provider<SpritePhysics>> spritePhysics) {
    this.logger = logger;
    this.projectileFactory = particleFactory;
    this.imageFactory = imageFactory;
    this.spritePhysics = spritePhysics;
  }

  @Override
  public Projectile parseFromXmlFile(String file) throws IOException {
    Element root = XmlResources.parse(getClass(), file);
    String projectileName = root.getAttribute("name");
    String startState = root.getAttribute("startState");
    List<ProjectileState> states = new ArrayList<>();

    for (Element state : root.getChildrenByName("state")) {
      String name = state.getAttribute("name");

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

      int damage = 0;
      String damageValue = state.getAttribute("damage", null);
      if (damageValue != null) {
        damage = Integer.parseInt(damageValue);
      }

      SpritePhysics physics = null;
      String move = state.getAttribute("move", null);
      if (move != null) {
        Provider<SpritePhysics> provider = spritePhysics.get(move);
        if (provider != null) {
          physics = provider.get();
        }
      }

      List<Image> images = parseParticleStateImages(state);
      states.add(
          projectileFactory.createProjectileState(
              name, images, ticks, loop, nextState, finalState, physics, damage));
    }

    return projectileFactory.createProjectile(projectileName, states, startState);
  }

  private List<Image> parseParticleStateImages(Element state) throws IOException {
    List<Image> images = new ArrayList<>();

    for (Element image : state.getChildrenByName("image")) {
      images.add(imageFactory.createImage(image.getAttribute("src")));
    }

    return images;
  }
}
