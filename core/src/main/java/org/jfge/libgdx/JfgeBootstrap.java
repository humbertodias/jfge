package org.jfge.libgdx;

public final class JfgeBootstrap {

  private JfgeBootstrap() {}

  public static JfgeLibGdxComponent createComponent() {
    return DaggerJfgeLibGdxComponent.create();
  }
}
