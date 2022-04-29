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
  private boolean invert;
  /**
   *
   * @param parameters size 3 array [relativeRow, absoluteRow, invert]
   */
  public IsInRow(int[] parameters) {
    super(parameters);
    relativeRow = getParameter(0);
    absoluteRow = getParameter(1);
    invert = getParameter(2) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int row = relativeRow + referencePoint.row();
    return invertIfTrue(row == absoluteRow, invert);
  }
}
