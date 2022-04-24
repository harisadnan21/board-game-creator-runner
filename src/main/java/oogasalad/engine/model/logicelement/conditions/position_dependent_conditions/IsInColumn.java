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

  private int relativeColumn;
  private int absoluteColumn;
  /**
   *
   * @param parameters size 2 array [relativeColumn, absoluteColumn]
   */
  public IsInColumn(int[] parameters) {
    super(parameters);
    relativeColumn = myParameters[0];
    absoluteColumn = myParameters[1];
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    relativeColumn += referencePoint.column();
    return relativeColumn == absoluteColumn;
  }
}
