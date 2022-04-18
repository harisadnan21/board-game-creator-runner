package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;

public class EasySearcher extends MinMaxSearcher {

  public EasySearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      oogasalad.engine.model.ai.AIOracle AIOracle) {
    super(maxDepth, stateEvaluator, AIOracle);
  }
}
