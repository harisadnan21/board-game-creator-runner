package oogasalad.engine.model.conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public abstract class Condition {

  protected int[] myParameters;

  public Condition(int[] parameters) {
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
