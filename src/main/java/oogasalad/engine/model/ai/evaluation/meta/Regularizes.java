package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface Regularizes {

  int regularizeEvaluationScore(StateEvaluator stateEvaluator, Board board, int player);

}
