package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * @author Jake Heller
 */
public class IsEmpty extends Condition {

  private int row;
  private int column;
  private boolean isAbsolute;
  /**
   * isAbsolute is 0 for relative, 1 for absolute
   * @param parameters size 3 array [row,column,isAbsolute]
   *
   */
  public IsEmpty(int[] parameters) {
    super(parameters);
    row = myParameters[0];
    column = myParameters[1];
    isAbsolute = myParameters[2] != 0;
  }


  /**
   *
   * @param board
   * @param referencePoint reference point for this condition
   * @return
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(row,column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return board.isEmpty(position.row(), position.column());
  }

}
