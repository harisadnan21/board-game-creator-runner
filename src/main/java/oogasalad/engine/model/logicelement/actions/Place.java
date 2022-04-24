package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class Place extends Action {

  private int row;
  private int column;
  private int type;
  private int player;
  private boolean isAbsolute;
  /**
   *
   * @param parameters size 4 array [row, column, type, player, isAbsolute]
   */
  public Place(int[] parameters) {
    super(parameters);
    row = myParameters[0];
    column = myParameters[1];
    type = myParameters[2];
    player = myParameters[3];
    isAbsolute = myParameters[4] != 0;
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
