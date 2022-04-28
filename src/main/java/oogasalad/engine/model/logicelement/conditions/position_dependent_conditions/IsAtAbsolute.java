package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Condition checks if relative point is equal to absolute point
 * @author Jake Heller
 */
public class IsAtAbsolute extends Condition {

  private int relativeRow;
  private int relativeColumn;
  private int absoluteRow;
  private int absoluteColumn;
  private boolean invert;

  /**
   *
   * @param parameters size 5 array [relativeRow, relativeColumn, absoluteRow, absoluteColumn, invert]
   */
  public IsAtAbsolute(int[] parameters) {
    super(parameters);
    relativeRow = getParameter(0);
    relativeColumn = getParameter(1);
    absoluteRow = getParameter(2);
    absoluteColumn = getParameter(3);
    invert = getParameter(4) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position relativePoint = new Position(relativeRow, relativeColumn);
    Position absolutePoint = new Position(absoluteRow, absoluteColumn);
    relativePoint = transformToRelative(referencePoint, relativePoint);
    return invertIfTrue(relativePoint.equals(absolutePoint), invert);
  }
}
