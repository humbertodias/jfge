package org.jfge.games.sf2.projectile;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import java.io.IOException; // For Hadouken constructor
import javax.inject.Singleton; // Hadouken provider is a Singleton
import javax.xml.parsers.ParserConfigurationException; // For Hadouken constructor
import org.jfge.api.projectile.Projectile;
import org.jfge.api.projectile.ProjectileParser; // For Hadouken constructor
import org.xml.sax.SAXException; // For Hadouken constructor

@Module
public class StreetFighter2ProjectileModule {

  // Hadouken is a Provider<Projectile> and is annotated with @Singleton.
  // Dagger will manage its single instance.
  @Provides
  @Singleton // Ensure the Hadouken provider instance is a singleton
  Hadouken provideHadoukenProvider(ProjectileParser projectileParser)
      throws ParserConfigurationException, SAXException, IOException {
    return new Hadouken(projectileParser);
  }

  @Provides
  @IntoMap
  @StringKey("hadouken")
  Projectile provideHadouken(Hadouken hadoukenProvider) {
    // The Projectile instance from hadoukenProvider.get() is a new instance
    // each time due to projectile.create() in Hadouken.get().
    return hadoukenProvider.get();
  }
}
