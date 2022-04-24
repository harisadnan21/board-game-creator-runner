package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

public class IsOccupied extends Condition {

  private int row;
  private int column;
  private boolean isAbsolute;
  /**
   * row,column are absolute position if isAbsolute is not 0
   * @param parameters size 3 array [row,column,isAbsolute]
   */
  public IsOccupied(int[] parameters) {
    super(parameters);
    row = myParameters[0];
    column = myParameters[1];
    isAbsolute = myParameters[2] != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return !board.isEmpty(position.row(), position.column());
  }
}
