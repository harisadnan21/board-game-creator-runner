package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Checks if relative row is at absolute row
 *
 * @author Jake Heller
 */
public class IsInRow extends Condition {

  /**
   *
   * @param parameters size 2 array [relativeRow, absoluteRow]
   */
  public IsInRow(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int relativeRow = myParameters[0] + referencePoint.row();
    int absoluteRow = myParameters[1];
    return relativeRow == absoluteRow;
  }
}
