package oogasalad.engine.model.ai.evaluation;

import oogasalad.engine.model.ai.exceptions.InvalidBoardException;
import oogasalad.engine.model.board.Board;

/**
 * @author Alex Bildner
 */
@FunctionalInterface
public interface StateEvaluator {

  Evaluation evaluate(Board board);

  static void throwIfInvalid(Board board) {
    if (board == null) {
      throw new InvalidBoardException();
    }
  }

}
