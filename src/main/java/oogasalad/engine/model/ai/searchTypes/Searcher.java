package oogasalad.engine.model.ai.searchTypes;

import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.Choice;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public abstract class Searcher {

  protected final int maxDepth;
  protected final int forPlayer;
  protected final StateEvaluator stateEvaluator;
  protected final AIOracle Oracle;

  protected Searcher(int maxDepth, int forPlayer, StateEvaluator stateEvaluator, AIOracle Oracle) {
    this.maxDepth = maxDepth;
    this.forPlayer = forPlayer;
    this.stateEvaluator = stateEvaluator;
    this.Oracle = Oracle;
  }


  public abstract Choice selectChoice(Board board);
}
