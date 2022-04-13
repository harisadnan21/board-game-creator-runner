package oogasalad.engine.model.AI.Evaluation;

import oogasalad.engine.model.AI.InvalidBoardException;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface StateEvaluator {

  public int evaluate(Board board, int player);

  public static void throwIfInvalid(Board board) {
    if (board == null) {
      throw new InvalidBoardException();
    }
  }

}
