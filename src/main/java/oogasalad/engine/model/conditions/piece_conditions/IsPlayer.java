package oogasalad.engine.model.conditions.piece_conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

/**
 *
 * @author Jake Heller
 */
public class IsPlayer extends PieceCondition {

  /**
   *
   * @param parameters size 1 array where parameters[0] is the player's number
   */
  public IsPlayer(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    return board.getPlayer() == myParameters[0];
  }
}
