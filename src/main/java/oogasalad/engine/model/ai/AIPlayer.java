package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.Selects;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.player.Player;


public class AIPlayer extends Player {

  private final int playerNumber;
  private final Selects selector;


  public AIPlayer(int playerNumberForAI, Selects selects) {
    super(null, null, null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.selector = selects;
  }

  public AIChoice chooseAction(Board board) {
    return selector.selectChoice(board, this.playerNumber);
  }


  @Override
  public void chooseMove() {
    Board board = super.getGameBoard();
    AIChoice AIChoice = this.chooseAction(board);
    super.executeMove(this, (Choice) AIChoice);
  }

  @Override
  public void onCellSelect(int i, int j) {
    throw new NoSuchMethodError("no");
  }
}
