package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

public class Remove extends Action {

  private int row;
  private int column;
  private boolean isAbsolute;
  /**
   *
   * @param parameters size 2 array [row, column, isAbsolute]
   */
  public Remove(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    isAbsolute = getParameter(2) != 0;
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    return board.remove(position.row(), position.column());
  }
}
