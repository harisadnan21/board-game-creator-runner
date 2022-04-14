package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.misc.OutOfBoardException;
import oogasalad.engine.model.board.boards.Board;

public class Remove extends Action {

  /**
   *
   * @param parameters size 2 array [i, j]
   */
  public Remove(int[] parameters) {
    super(parameters);
  }
  @Override
  public Board execute(Board board, int refI, int refJ)
      throws OutOfBoardException {
    return board.removePiece(myParameters[0]+refI, myParameters[1]+refJ);
  }
}
