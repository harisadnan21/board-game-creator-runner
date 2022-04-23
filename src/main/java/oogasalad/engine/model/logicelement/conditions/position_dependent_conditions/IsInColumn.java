package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Checks if relative column is at absolute column
 *
 * @author Jake Heller
 */
public class IsInColumn extends Condition {

  /**
   *
   * @param parameters size 2 array [relativeColumn, absoluteColumn]
   */
  public IsInColumn(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int relativeColumn = myParameters[0] + referencePoint.column();
    int absoluteColumn = myParameters[1];
    return relativeColumn == absoluteColumn;
  }
}
