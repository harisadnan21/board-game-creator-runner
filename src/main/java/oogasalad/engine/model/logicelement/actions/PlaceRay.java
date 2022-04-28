package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Places pieces of given type and player in a ray
 *
 * @author Jake Heller
 */
public class PlaceRay extends Action {

  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private int length;
  private int player;
  private int type;
  private boolean isAbsolute;

  /**
   *
   * @param parameters size 7 array [startRow, startColumn, rowDirection, columnDirection, length, player, id, isAbsolute]
   */
  public PlaceRay(int[] parameters) {
    super(parameters);
    startRow = getParameter(0);
    startColumn = getParameter(1);
    rowDirection = getParameter(2);
    columnDirection = getParameter(3);
    length = getParameter(4);
    player = getParameter(5);
    type = getParameter(6);
    isAbsolute = getParameter(7) != 0;
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
        board = board.placeNewPiece(position.row(), position.column(), type, player);
      }
      position = position.add(direction);
    }
    return board;
  }
}
