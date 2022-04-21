package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Position;

public class SetActivePlayer extends Action {

  /**
   *
   * @param parameters size 1 array where [player] is the new active player in the board
   */
  public SetActivePlayer(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    return board.setPlayer(board.getPlayer() + myParameters[0]);
  }
}
