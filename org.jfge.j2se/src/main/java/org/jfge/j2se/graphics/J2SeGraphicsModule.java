package org.jfge.j2se.graphics;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.jfge.api.engine.EngineModule;
import org.jfge.j2se.controller.J2SeKeyboardController1;
import org.jfge.j2se.controller.J2SeKeyboardController2;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

/** The Class GraphicsModule. */
public final class J2SeGraphicsModule extends AbstractModule {

  /* (non-Javadoc)
   * @see com.google.inject.AbstractModule#configure()
   */
  @Override
  protected void configure() {
    loadProperties(binder());
    /*
     * binding graphics provider, image factory and collision strategy implementations
     */
    bind(GraphicsProvider.class).to(J2SeGraphicsProvider.class);
    bind(GraphicsFactory.class).to(J2SeGraphicsFactory.class);

    /*
     * making keyboard controller available via multibind
     */
    bind(Controller.class)
        .annotatedWith(Names.named("keyboard.controller1"))
        .to(J2SeKeyboardController1.class);
    bind(Controller.class)
        .annotatedWith(Names.named("keyboard.controller2"))
        .to(J2SeKeyboardController2.class);

    MapBinder<String, Controller> controllerBinder =
        MapBinder.newMapBinder(binder(), String.class, Controller.class);
    controllerBinder.addBinding("keyboard.controller1").to(J2SeKeyboardController1.class);
    controllerBinder.addBinding("keyboard.controller2").to(J2SeKeyboardController2.class);
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
