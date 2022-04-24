package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Condition checks if relative point is equal to absolute point
 * @author Jake Heller
 */
public class IsAtAbsolute extends Condition {

  /**
   *
   * @param parameters size-4 array [relativeRow, relativeColumn, absoluteRow, absoluteColumn]
   */
  public IsAtAbsolute(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position relativePoint = new Position(myParameters[0], myParameters[1]);
    Position absolutePoint = new Position(myParameters[2], myParameters[3]);
    relativePoint = transformToRelative(referencePoint, relativePoint);
    return relativePoint.equals(absolutePoint);
  }
}
