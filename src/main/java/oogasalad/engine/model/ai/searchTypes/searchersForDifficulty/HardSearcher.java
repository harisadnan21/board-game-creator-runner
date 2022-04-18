package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.DifficultyDepthConstants;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.AlphaBetaSearcher;

public class HardSearcher extends AlphaBetaSearcher {

  public HardSearcher(StateEvaluator stateEvaluator, AIOracle aiOracle) {
    super(DifficultyDepthConstants.HARD, stateEvaluator, aiOracle);
  }
}
