package org.jfge.ext.scene;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.jfge.ext.render.LoadingSceneRendererImpl;
import org.jfge.spi.render.LoadingSceneRenderer;
import org.jfge.spi.scene.Scene;

@Module
public abstract class SceneModule {

  @Binds
  abstract LoadingSceneRenderer bindLoadingSceneRenderer(LoadingSceneRendererImpl renderer);

  @Provides
  @IntoMap
  @StringKey("loadingScreen")
  static Scene loadingScene(LoadingSceneImpl scene) {
    return scene;
  }
}
