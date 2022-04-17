package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.searchTypes.EasySearcher;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;
import oogasalad.engine.model.ai.searchTypes.RandomSearcher;
import oogasalad.engine.model.ai.searchTypes.SearchType;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.Searcher;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.utilities.Pair;


public class AIPlayer extends Player {

  private final int playerNumber;
  private StateEvaluator stateEvaluator;
  private final Difficulty difficulty;
  private final SearchType searchType;
  private final AIOracle AIOracle;
  private Searcher searcher;

  public AIPlayer(int playerNumberForAI, StateEvaluator stateEvaluator, Difficulty difficulty,
      SearchType searchType, AIOracle AIOracle) {
    super(null); // should be engine
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


  @Override @Deprecated
  //Oracle needs to be passed in at construction: AI should not change which Oracle it uses during the game
  //AI should not know/care about having a Pair, it will return a "Choice" allows us encapsulate any necessary information for move
  public Pair<Position, Move> chooseMove(Engine oracle, Board board) {
    return null;
  }
}
