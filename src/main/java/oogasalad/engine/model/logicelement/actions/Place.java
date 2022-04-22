package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class Place extends Action {

  /**
   *
   * @param parameters size 4 array [i, j, type, player]
   */
  public Place(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    int i = myParameters[0] + referencePoint.i();
    int j = myParameters[1] + referencePoint.j();
    return board.placeNewPiece(i, j,
        myParameters[2], myParameters[3]);
  }
}
