package oogasalad.engine.model.conditions.piece_conditions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public abstract class PieceCondition {

  protected int[] myParameters;

  public PieceCondition(int[] parameters) {
    myParameters = parameters;
  }

  /**
   *
   * @param board
   * @param refI reference i
   * @param refJ reference j
   * @throws OutOfBoardException
   */
  public abstract boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException;
}
