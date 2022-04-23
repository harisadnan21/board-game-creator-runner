package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * @author Jake Heller
 */
public class IsEmpty extends Condition {

  private int row;
  private int column;
  private boolean isAbsolute;
  private boolean invert;
  /**
   * isAbsolute is 0 for relative, 1 for absolute
   * @param parameters size 4 array [row, column, isAbsolute, invert]
   *
   */
  public IsEmpty(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    isAbsolute = getParameter(2) != 0;
    invert = getParameter(3) != 0;
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
      return invertIfTrue(false, invert);
    }
    return invertIfTrue(board.isEmpty(position.row(), position.column()), invert);
  }

}
