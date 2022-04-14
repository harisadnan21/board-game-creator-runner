package oogasalad.engine.model.ai.evaluation;

import oogasalad.engine.model.ai.exceptions.InvalidBoardException;
import oogasalad.engine.model.board.boards.Board;

@FunctionalInterface
public interface StateEvaluator {

  public int evaluate(Board board, int player);

  public static void throwIfInvalid(Board board) {
    if (board == null) {
      throw new InvalidBoardException();
    }
  }

}
