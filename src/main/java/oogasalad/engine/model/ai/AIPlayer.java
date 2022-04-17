package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.searchTypes.SearchType;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.Searcher;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
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
    super(null, null, null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.stateEvaluator = stateEvaluator;
    this.difficulty = difficulty;
    this.searchType = searchType;
    this.AIOracle = AIOracle;
    this.searcher = SearcherFactory.makeSearcher(this);
  }

  public AIChoice chooseAction(Board board) {
    return searcher.selectChoice(board);
  }


  @Override
  public void chooseMove() {
    Board board = super.getGameBoard();
    AIChoice AIChoice = this.chooseAction(board);
<<<<<<< Updated upstream
    super.executeMove(this, new Choice(null, null));
=======
    super.executeMove(this, AIChoice);
>>>>>>> Stashed changes
  }

  @Override
  public void onCellSelect(int i, int j) {
    throw new NoSuchMethodError("no");
  }
}
