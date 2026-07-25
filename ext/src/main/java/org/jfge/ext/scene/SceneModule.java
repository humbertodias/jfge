package org.jfge.ext.scene;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.jfge.ext.render.LoadingSceneRendererImpl;
import org.jfge.spi.render.LoadingSceneRenderer;
import org.jfge.spi.scene.Scene;

public class SceneModule extends AbstractModule {

  @Override
  protected void configure() {
    MapBinder<String, Scene> sceneBinder =
        MapBinder.newMapBinder(binder(), String.class, Scene.class);
    sceneBinder.addBinding("loadingScreen").to(LoadingSceneImpl.class);
    bind(LoadingSceneRenderer.class).to(LoadingSceneRendererImpl.class);
  }
}
