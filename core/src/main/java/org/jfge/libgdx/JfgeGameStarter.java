package org.jfge.libgdx;

public final class JfgeGameStarter {

  private JfgeGameStarter() {}

  public static JfgeApplication forGameKey(String gameKey) {
    return new JfgeApplication(gameKey);
  }
}
