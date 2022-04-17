package oogasalad.engine.model.ai.searchTypes;

import oogasalad.engine.model.AIOracle;
import oogasalad.engine.model.ai.Choice;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public abstract class Searcher {

  protected final int maxDepth;
  protected final int forPlayer;
  protected final StateEvaluator stateEvaluator;
  protected final AIOracle AIOracle;

  protected Searcher(int maxDepth, int forPlayer, StateEvaluator stateEvaluator, AIOracle AIOracle) {
    this.maxDepth = maxDepth;
    this.forPlayer = forPlayer;
    this.stateEvaluator = stateEvaluator;
    this.AIOracle = AIOracle;
  }

  abstract Choice selectChoice(Board board);
}
