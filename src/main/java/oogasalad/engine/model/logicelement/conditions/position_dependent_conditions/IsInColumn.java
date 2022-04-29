package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Checks if relative column is at absolute column
 *
 * @author Jake Heller
 */
public class IsInColumn extends Condition {

  private int relativeColumn;
  private int absoluteColumn;
  private boolean invert;
  /**
   *
   * @param parameters size 3 array [relativeColumn, absoluteColumn, invert]
   */
  public IsInColumn(int[] parameters) {
    super(parameters);
    relativeColumn = getParameter(0);
    absoluteColumn = getParameter(1);
    invert = getParameter(2) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int column = relativeColumn + referencePoint.column();
    return invertIfTrue(column == absoluteColumn, invert);
  }
}
