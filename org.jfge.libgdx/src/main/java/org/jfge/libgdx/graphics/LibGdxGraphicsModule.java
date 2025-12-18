package org.jfge.libgdx.graphics;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.jfge.api.engine.EngineModule;
import org.jfge.libgdx.controller.LibGdxKeyboardController1;
import org.jfge.libgdx.controller.LibGdxKeyboardController2;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

public final class LibGdxGraphicsModule extends AbstractModule {

  @Override
  protected void configure() {
    loadProperties(binder());
    
    // Binding graphics provider and factory
    bind(GraphicsProvider.class).to(LibGdxGraphicsProvider.class);
    bind(GraphicsFactory.class).to(LibGdxGraphicsFactory.class);

    // Binding keyboard controllers
    bind(Controller.class)
        .annotatedWith(Names.named("keyboard.controller1"))
        .to(LibGdxKeyboardController1.class);
    bind(Controller.class)
        .annotatedWith(Names.named("keyboard.controller2"))
        .to(LibGdxKeyboardController2.class);

    MapBinder<String, Controller> controllerBinder =
        MapBinder.newMapBinder(binder(), String.class, Controller.class);
    controllerBinder.addBinding("keyboard.controller1").to(LibGdxKeyboardController1.class);
    controllerBinder.addBinding("keyboard.controller2").to(LibGdxKeyboardController2.class);
  }

  private void loadProperties(Binder binder) {
    InputStream stream =
        EngineModule.class.getResourceAsStream("/org/jfge/config/controller/keyboard.properties");
    Properties engineProperties = new Properties();
    try {
      engineProperties.load(stream);
      Names.bindProperties(binder, engineProperties);
    } catch (IOException e) {
      binder.addError(e);
    }
  }
}
