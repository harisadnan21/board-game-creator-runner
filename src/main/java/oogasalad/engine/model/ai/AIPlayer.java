package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.searchTypes.EasySearcher;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;
import oogasalad.engine.model.ai.searchTypes.RandomSearcher;
import oogasalad.engine.model.ai.searchTypes.SearchType;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.Searcher;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;


public class AIPlayer extends Player {

  private final int playerNumber;
  private StateEvaluator stateEvaluator;
  private final Difficulty difficulty;
  private final SearchType searchType;
  private final AIOracle AIOracle;
  private Searcher searcher;


  public AIPlayer(int playerNumberForAI, StateEvaluator stateEvaluator, Difficulty difficulty,
      SearchType searchType, AIOracle AIOracle) {
    super(null, null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.stateEvaluator = stateEvaluator;
    this.difficulty = difficulty;
    this.searchType = searchType;
    this.AIOracle = AIOracle;
    this.searcher = this.makeSearcher();
  }

  // TODO: clean this up
  private Searcher makeSearcher() {
    return switch (this.difficulty) {
      case RANDOM -> new RandomSearcher(1, playerNumber, stateEvaluator, AIOracle);
      case EASY -> new EasySearcher(1, playerNumber, stateEvaluator, AIOracle);
      case MEDIUM -> new MinMaxSearcher(3, playerNumber, stateEvaluator, AIOracle, null);
      case HARD -> new MinMaxSearcher(5, playerNumber, stateEvaluator, AIOracle, null);
      case EXPERT -> new MinMaxSearcher(8, playerNumber, stateEvaluator, AIOracle, null);
    };
  }

  public Choice chooseAction(Board board) {
    return searcher.selectChoice(board);
  }


  @Override
  public oogasalad.engine.model.engine.Choice chooseMove() {
    return null;
  }

  @Override
  public void onCellSelect(int i, int j) {

  }
}
