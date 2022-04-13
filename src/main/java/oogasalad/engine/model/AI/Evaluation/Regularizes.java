package oogasalad.engine.model.AI.Evaluation;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;

@FunctionalInterface
public interface Regularizes {

  public int regularizeEvaluationScore(StateEvaluator stateEvaluator, Board board, int player);

}
