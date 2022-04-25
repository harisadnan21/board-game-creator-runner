package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * This condition always evaluates to the same result
 * @author Jake Heller, Ricky Weerts
 */
public class Constant extends BoardCondition {
  private boolean returnsTrue;

  /**
   *
   * @param parameters size 1 array [returnsTrue]
   */
  public Constant(int[] parameters) {
    super(parameters);
    returnsTrue = getParameter(0) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return returnsTrue;
  }
}
