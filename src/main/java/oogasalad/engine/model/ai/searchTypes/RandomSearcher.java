package oogasalad.engine.model.ai.searchTypes;

import oogasalad.engine.model.AIOracle;
import oogasalad.engine.model.ai.Choice;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class RandomSearcher extends Searcher{

  protected RandomSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      AIOracle AIOracle) {
    super(maxDepth, forPlayer, stateEvaluator, AIOracle);
  }

  @Override
  Choice selectChoice(Board board) {
    return null;
  }
}
