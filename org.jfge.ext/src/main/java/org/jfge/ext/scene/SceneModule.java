package org.jfge.ext.scene;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.inject.Singleton; // LoadingSceneImpl is a Singleton
import org.jfge.ext.render.LoadingSceneRendererImpl;
import org.jfge.spi.render.LoadingSceneRenderer;
import org.jfge.spi.scene.Scene;

@Module
public abstract class SceneModule {

  @Binds
  abstract LoadingSceneRenderer bindLoadingSceneRenderer(LoadingSceneRendererImpl impl);

  // LoadingSceneImpl is @Singleton, so Dagger will manage its single instance.
  @Provides
  @IntoMap
  @StringKey("loadingScreen")
  static Scene provideLoadingScene(LoadingSceneImpl scene) {
    return scene;
  }
}
