package oogasalad.engine.model.conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public class IsOccupied extends PieceCondition {

  /**
   *
   * @param parameters
   */
  public IsOccupied(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    return !board.isEmpty(myParameters[0]+refI, myParameters[1]+refJ);
  }
}
