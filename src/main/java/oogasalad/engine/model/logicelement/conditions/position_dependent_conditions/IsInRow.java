package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Checks if relative row is at absolute row
 *
 * @author Jake Heller
 */
public class IsInRow extends Condition {

  private int relativeRow;
  private int absoluteRow;
  /**
   *
   * @param parameters size 2 array [relativeRow, absoluteRow]
   */
  public IsInRow(int[] parameters) {
    super(parameters);
    relativeRow = myParameters[0];
    absoluteRow = myParameters[1];
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    relativeRow += referencePoint.row();
    return relativeRow == absoluteRow;
  }
}
