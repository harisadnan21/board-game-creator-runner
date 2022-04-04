package oogasalad.engine.model.action;

import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Board;

public class Place extends Action {

  /**
   *
   * @param parameters size 4 array [i, j, type, player]
   */
  public Place(int[] parameters) {
    super(parameters);
  }

  @Override
  public void execute(Board board, int refI, int refJ) throws OutOfBoardException {
    board.placeNewPiece(myParameters[0]+refI, myParameters[1]+refJ,
        myParameters[2], myParameters[3]);
  }
}
