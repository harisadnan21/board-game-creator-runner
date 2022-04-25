package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Returns true if active player is equal to the given player
 *
 * @author Jake Heller
 */
public class IsPlayer extends BoardCondition {
  private int player;
  private boolean invert;

  /**
   *
   * @param parameters size 2 array [player, invert]
   */
  public IsPlayer(int[] parameters) {
    super(parameters);
    player = getParameter(0);
    invert = getParameter(1) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return invertIfTrue(board.getPlayer() == player, invert);
  }
}
