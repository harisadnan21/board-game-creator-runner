package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

public class Place extends Action {

  private int row;
  private int column;
  private int type;
  private int player;
  private boolean isAbsolute;
  /**
   *
   * @param parameters size 5 array [row, column, type, player, isAbsolute]
   */
  public Place(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    type = getParameter(2);
    player = getParameter(3);
    isAbsolute = getParameter(4) != 0;
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    return board.placeNewPiece(position.row(), position.column(),
        type, player);
  }
}
