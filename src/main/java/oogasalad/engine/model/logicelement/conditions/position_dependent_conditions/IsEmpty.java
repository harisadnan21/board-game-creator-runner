package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * @author Jake Heller
 */
public class IsEmpty extends Condition {

  /**
   * isAbsolute is 0 for relative, 1 for absolute
   * @param parameters size 3 array [i, j, isAbsolute]
   *
   */
  public IsEmpty(int[] parameters) {
    super(parameters);
  }


  /**
   *
   * @param board
   * @param referencePoint reference point for this condition
   * @return
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(myParameters[0], myParameters[1]);
    if (myParameters[2] == 0) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return board.isEmpty(position.row(), position.column());
  }

}
