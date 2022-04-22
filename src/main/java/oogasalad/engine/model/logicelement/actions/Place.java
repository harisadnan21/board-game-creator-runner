package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class Place extends Action {

  /**
   *
   * @param parameters size 4 array [row, column, type, player, isAbsolute]
   */
  public Place(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position position = new Position(myParameters[0], myParameters[1]);
    if (myParameters[4] == 0) {
      position = transformToRelative(position, referencePoint);
    }
    return board.placeNewPiece(position.row(), position.column(),
        myParameters[2], myParameters[3]);
  }
}
