package oogasalad.engine.model.ai;

import oogasalad.engine.model.AI.SearchType;
import oogasalad.engine.model.Oracle;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
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
  private final Oracle oracle;

  public AIPlayer(int playerNumberForAI, StateEvaluator stateEvaluator, Difficulty difficulty,
      SearchType searchType, Oracle oracle) {
    super(null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.stateEvaluator = stateEvaluator;
    this.difficulty = difficulty;
    this.searchType = searchType;
    this.oracle = oracle;
  }

  public Choice chooseAction(Board board) {
    return null; //TODO: implement move choosing
  }


  @Override @Deprecated
  //Oracle needs to be passed in at construction: AI should not change which Oracle it uses during the game
  //AI should not know/care about having a Pair, it will return a "Choice" allows us encapsulate any necessary information for move
  public Pair<Position, Move> chooseMove(Engine oracle, Board board) {
    return null;
  }
}
