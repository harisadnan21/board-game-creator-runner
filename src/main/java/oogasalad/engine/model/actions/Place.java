package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.misc.OutOfBoardException;
import oogasalad.engine.model.board.boards.Board;

public class Place extends Action {

  /**
   *
   * @param parameters size 4 array [i, j, type, player]
   */
  public Place(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, int refI, int refJ) throws OutOfBoardException {
    return board.placeNewPiece(myParameters[0]+refI, myParameters[1]+refJ,
        myParameters[2], myParameters[3]);
  }
}
