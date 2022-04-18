package oogasalad.engine.model.ai.searchTypes.searchersForDifficulty;

import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.DifficultyDepthConstants;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;

public class EasySearcher extends MinMaxSearcher {

  public EasySearcher(StateEvaluator stateEvaluator, AIOracle aiOracle) {
    super(DifficultyDepthConstants.EASY, stateEvaluator, aiOracle);
  }
}
