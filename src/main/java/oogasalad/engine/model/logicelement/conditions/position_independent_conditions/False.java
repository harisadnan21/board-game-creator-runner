package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * This condition always evaluates to false
 * @author Jake Heller
 */
public class False extends BoardCondition {

  /**
   *
   * @param parameters size 0 array []
   */
  public False(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return false;
  }
}
