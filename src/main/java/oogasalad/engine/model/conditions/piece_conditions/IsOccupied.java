package oogasalad.engine.model.conditions.piece_conditions;

import oogasalad.engine.model.board.OutOfBoardException;
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
    int i = myParameters[0]+refI; //TODO: why are i & j assigned values but never used, is there a mistake here?
    int j = myParameters[1]+refJ;
    return !board.isEmpty(myParameters[0]+refI, myParameters[1]+refJ);
  }
}
