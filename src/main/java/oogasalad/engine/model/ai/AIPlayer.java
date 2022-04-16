package oogasalad.engine.model.ai;

import oogasalad.engine.model.Oracle;
import oogasalad.engine.model.actions.Action;
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

  public AIPlayer(int playerNumber, StateEvaluator stateEvaluator, Difficulty difficulty) {
    super(null, null); // should be engine
    this.playerNumber = playerNumber;
    this.stateEvaluator = stateEvaluator;
    this.difficulty = difficulty;
  }

  public Choice chooseMove(Board board) {
    return null; //TODO: implement move choosing
  }


  @Override
  public Pair<Position, Move> chooseMove() {
    return null;
  }

  @Override
  public void onCellSelect(int i, int j) {

  }
}
