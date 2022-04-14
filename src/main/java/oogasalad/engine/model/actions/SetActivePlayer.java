package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.misc.OutOfBoardException;

public class SetActivePlayer extends Action {

  /**
   *
   * @param parameters size 1 array where [player] is the new active player in the board
   */
  public SetActivePlayer(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, int refI, int refJ) throws OutOfBoardException {
    return board.setPlayer(board.getPlayer() + myParameters[0]);
  }
}
