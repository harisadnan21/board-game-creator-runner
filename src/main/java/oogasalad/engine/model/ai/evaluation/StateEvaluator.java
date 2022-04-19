package oogasalad.engine.model.ai.evaluation;

import oogasalad.engine.model.ai.exceptions.InvalidBoardException;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface StateEvaluator {

  int evaluate(Board board, int player);

  static void throwIfInvalid(Board board) {
    if (board == null) {
      throw new InvalidBoardException();
    }
  }

}
