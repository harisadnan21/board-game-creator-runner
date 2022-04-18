package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;

public class AdaptiveSearcher extends MinMaxSearcher {

  public AdaptiveSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      oogasalad.engine.model.ai.AIOracle AIOracle) {
    super(maxDepth, forPlayer, stateEvaluator, AIOracle);
  }
}
