package org.jfge.games.sf2.projectile;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.projectile.ProjectileParser;

@Singleton
public final class Hadouken implements Provider<Projectile> {

  private Projectile projectile;

  private ProjectileParser projectileParser;

  @Inject
  public Hadouken(ProjectileParser projectileParser) {
    this.projectileParser = projectileParser;
  }

  @Override
  public Projectile get() {
    if (this.projectile == null) {
      try {
        this.projectile =
            projectileParser.parseFromXmlFile(
                "/org/jfge/games/sf2/projectile/shoryuken/hadouken.xml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return projectile.create();
  }
}
