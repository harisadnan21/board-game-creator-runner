package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.searchTypes.EasySearcher;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;
import oogasalad.engine.model.ai.searchTypes.RandomSearcher;
import oogasalad.engine.model.ai.searchTypes.Searcher;

public class SearcherFactory {// TODO: clean this up

  public static Searcher makeSearcher(AIPlayer aiPlayer) {
    return switch (aiPlayer.difficulty) {
      case RANDOM -> new RandomSearcher(1, aiPlayer.playerNumber, aiPlayer.stateEvaluator,
          aiPlayer.AIOracle);
      case EASY -> new EasySearcher(1, aiPlayer.playerNumber, aiPlayer.stateEvaluator,
          aiPlayer.AIOracle);
      case MEDIUM -> new MinMaxSearcher(3, aiPlayer.playerNumber, aiPlayer.stateEvaluator,
          aiPlayer.AIOracle, null);
      case HARD -> new MinMaxSearcher(5, aiPlayer.playerNumber, aiPlayer.stateEvaluator,
          aiPlayer.AIOracle, null);
      case EXPERT -> new MinMaxSearcher(8, aiPlayer.playerNumber, aiPlayer.stateEvaluator,
          aiPlayer.AIOracle, null);
    };
  }
}