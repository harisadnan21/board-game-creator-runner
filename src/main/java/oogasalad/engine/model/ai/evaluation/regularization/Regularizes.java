package oogasalad.engine.model.ai.evaluation.regularization;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface Regularizes {

  public int regularizeEvaluationScore(StateEvaluator stateEvaluator, Board board, int player);

}
