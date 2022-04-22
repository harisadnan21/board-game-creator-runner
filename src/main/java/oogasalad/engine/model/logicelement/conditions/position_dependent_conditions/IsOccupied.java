package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

public class IsOccupied extends Condition {

  /**
   * i,j are absolute position if isAbsolute is not 0
   * @param parameters size 3 array [i,j,isAbsolute]
   */
  public IsOccupied(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(myParameters[0], myParameters[1]);
    if (myParameters[2] == 0) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return !board.isEmpty(position.row(), position.column());
  }
}
