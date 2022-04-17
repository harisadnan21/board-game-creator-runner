package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class CenterAround implements Regularizes {
  private int center;

  public CenterAround(int center) {
    this.center = center;
  }

  @Override
  public int regularizeEvaluationScore(StateEvaluator stateEvaluator, Board board, int player) {
    int originalScore = stateEvaluator.evaluate(board, player);
    return -1 * Math.abs(center - originalScore);
  }
}
