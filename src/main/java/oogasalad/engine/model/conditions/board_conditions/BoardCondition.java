package oogasalad.engine.model.conditions.board_conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public interface BoardCondition {



  /**
   *
   * @param board
   * @throws OutOfBoardException
   */
  public abstract boolean isTrue(Board board) throws OutOfBoardException;
}
