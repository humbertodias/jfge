package org.jfge.api.render;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RenderModule {

  @Binds
  abstract SpriteRenderer bindSpriteRenderer(SpriteRendererImpl impl);
}
