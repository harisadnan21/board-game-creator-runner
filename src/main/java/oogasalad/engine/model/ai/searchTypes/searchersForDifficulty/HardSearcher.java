package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.AlphaBetaSearcher;

public class HardSearcher extends AlphaBetaSearcher {

  public HardSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      oogasalad.engine.model.ai.AIOracle Oracle) {
    super(maxDepth, stateEvaluator, Oracle);
  }
}
