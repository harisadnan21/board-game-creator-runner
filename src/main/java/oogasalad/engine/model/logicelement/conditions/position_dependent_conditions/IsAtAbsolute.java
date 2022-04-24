package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import javafx.geometry.Pos;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
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

  /**
   *
   * @param parameters size-4 array [relativeRow, relativeColumn, absoluteRow, absoluteColumn]
   */
  public IsAtAbsolute(int[] parameters) {
    super(parameters);
    relativeRow = myParameters[0];
    relativeColumn = myParameters[1];
    absoluteRow = myParameters[2];
    absoluteColumn = myParameters[3];

  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position relativePoint = new Position(relativeRow, relativeColumn);
    Position absolutePoint = new Position(absoluteRow, absoluteColumn);
    relativePoint = transformToRelative(referencePoint, relativePoint);
    return relativePoint.equals(absolutePoint);
  }
}
