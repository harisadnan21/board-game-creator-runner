package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Removes pieces in a ray
 *
 * @author Jake Heller
 */
public class RemoveRay extends Action {
  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private int length;
  private boolean isAbsolute;

  /**
   *
   * @param parameters size 6 array [startRow, startColumn, rowDirection, columnDirection, length, isAbsolute]
   */
  public RemoveRay(int[] parameters) {
    super(parameters);
    startRow = getParameter(0);
    startColumn = getParameter(1);
    rowDirection = getParameter(2);
    columnDirection = getParameter(3);
    length = getParameter(4);
    isAbsolute = getParameter(5) != 0;
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position position = new Position(startRow, startColumn);
    Position direction = new Position(rowDirection, columnDirection);
    if (!isAbsolute) {
      position = position.add(referencePoint);
    }

    for (int i = 0; i < length; i++) {
      if (board.isValidPosition(position)) {
        board = board.remove(position.row(), position.column());
      }
      position = position.add(direction);
    }
    return board;
  }
}
