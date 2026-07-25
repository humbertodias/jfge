package org.jfge.libgdx;

import com.google.inject.Module;
import com.google.inject.util.Modules;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jfge.api.ai.AiModule;
import org.jfge.api.arena.ArenaModule;
import org.jfge.api.collision.CollisionModule;
import org.jfge.api.effect.EffectModule;
import org.jfge.api.engine.EngineModule;
import org.jfge.api.fighter.FighterModule;
import org.jfge.api.game.GameModule;
import org.jfge.api.projectile.ProjectileModule;
import org.jfge.api.render.RenderModule;
import org.jfge.ext.physics.PhysicsModule;
import org.jfge.ext.scene.SceneModule;
import org.jfge.libgdx.graphics.LibGdxGraphicsModule;

public final class JfgeBootstrap {

  private JfgeBootstrap() {}

  public static Module[] baseModules() {
    return new Module[] {
      Modules.override(new EngineModule()).with(new LibGdxEngineModule()),
      new LibGdxGraphicsModule(),
      new CollisionModule(),
      new EffectModule(),
      new PhysicsModule(),
      new FighterModule(),
      new SceneModule(),
      new AiModule(),
      new ProjectileModule(),
      new ArenaModule(),
      new RenderModule(),
      new GameModule()
    };
  }

  public static Module[] combine(Module[] baseModules, Module... gameModules) {
    List<Module> modules = new ArrayList<>(Arrays.asList(baseModules));
    modules.addAll(Arrays.asList(gameModules));
    return modules.toArray(new Module[0]);
  }
}
