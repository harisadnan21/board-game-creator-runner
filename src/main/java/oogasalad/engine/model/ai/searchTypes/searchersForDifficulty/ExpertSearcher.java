package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.AlphaBetaSearcher;

public class ExpertSearcher extends AlphaBetaSearcher {

  public ExpertSearcher(int maxDepth, int forPlayer,
      StateEvaluator stateEvaluator,
      oogasalad.engine.model.ai.AIOracle Oracle) {
    super(maxDepth, forPlayer, stateEvaluator, Oracle);
  }
}
