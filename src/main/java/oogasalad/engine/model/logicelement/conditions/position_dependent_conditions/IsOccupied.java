package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

public class IsOccupied extends Condition {

  private int row;
  private int column;
  private boolean isAbsolute;
  private boolean invert;
  /**
   * row,column are absolute position if isAbsolute is not 0
   * @param parameters size 4 array [row, column, isAbsolute, invert]
   */
  public IsOccupied(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    isAbsolute = getParameter(2) != 0;
    invert = getParameter(3) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return invertIfTrue(false, invert);
    }
    return invertIfTrue(board.isOccupied(position.row(), position.column()), invert);
  }
}
