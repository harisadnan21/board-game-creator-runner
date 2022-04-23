package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.moveSelection.Selects;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.player.Player;


public class AIPlayer extends Player {

  private final int playerNumber;
  private final Selects selector;
  private Oracle oracle;


  public AIPlayer(int playerNumberForAI, Selects selects) {
    super(null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.selector = selects;
  }

  public AIChoice chooseAction(Board board) {
    return selector.selectChoice(board, this.playerNumber);
  }


  @Override
  public void setGameBoard(Board activeBoard) {
    super.setGameBoard(activeBoard);
    Board board = getGameBoard();
    AIChoice AIChoice = this.chooseAction(board);
    executeMove(this, (Choice) AIChoice); //Fix unsafe cast
  }

  protected Oracle getOracle() {
    return oracle;
  }
}
