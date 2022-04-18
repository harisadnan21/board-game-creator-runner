package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.DifficultyDepthConstants;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;

public class MediumSearcher extends MinMaxSearcher {

  public MediumSearcher(StateEvaluator stateEvaluator, AIOracle aiOracle) {
    super(DifficultyDepthConstants.MEDIUM, stateEvaluator, aiOracle);
  }
}
