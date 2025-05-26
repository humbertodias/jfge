package org.jfge.api.ai;

import org.jfge.api.fighter.Fighter;

/** The Interface AiController. */
public interface AiController {

  /** The Constant NEAR. */
  public static final String NEAR = "near";

  /** The Constant MID. */
  public static final String MID = "mid";

  /** The Constant FAR. */
  public static final String FAR = "far";

  /**
   * Handle.
   *
   * @param obsrvFighter the obsrv sprite
   * @param oppFighter the opp sprite
   * @return true, if successful
   */
  public boolean handle(Fighter obsrvFighter, Fighter oppFighter);
}
