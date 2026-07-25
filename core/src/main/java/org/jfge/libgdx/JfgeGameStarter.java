package org.jfge.libgdx;

public final class JfgeGameStarter {

  private JfgeGameStarter() {}

  public static JfgeApplication forGameKey(JfgeLibGdxComponent component, String gameKey) {
    return new JfgeApplication(component, gameKey);
  }
}
