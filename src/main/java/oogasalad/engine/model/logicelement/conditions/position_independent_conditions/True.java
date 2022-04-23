package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

/**
 * This condition always returns true
 * @author Jake Heller
 */
public class True extends BoardCondition{

  /**
   *
   * @param parameters size 0 array
   */
  public True(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return true;
  }
}
