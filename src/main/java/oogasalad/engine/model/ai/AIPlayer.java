package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;

public class AIPlayer extends Player {

  private final int playerNumber;
  private StateEvaluator stateEvaluator;
  private final Difficulty difficulty;

  public AIPlayer(int playerNumber, StateEvaluator stateEvaluator, Difficulty difficulty) {
    this.playerNumber = playerNumber;
    this.stateEvaluator = stateEvaluator;
    this.difficulty = difficulty;
  }

  public Choice chooseMove(Board board) {
    return null; //TODO: implement move choosing
  }

}
