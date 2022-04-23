package oogasalad.engine.model.ai;

import oogasalad.engine.model.ai.moveSelection.Selects;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.player.AbstractPlayer;


public class AIPlayer extends AbstractPlayer {

  private final int playerNumber;
  private final Selects selector;


  public AIPlayer(int playerNumberForAI, Selects selects) {
    super(null, null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.selector = selects;
  }

  public AIChoice chooseAction(Board board) {
    return selector.selectChoice(board, this.playerNumber);
  }


  @Override
  public void chooseMove(Board activeBoard) {
    setGameBoard(activeBoard);
    Board board = getGameBoard();
    AIChoice AIChoice = this.chooseAction(board);
    executeMove(this, (Choice) AIChoice); //Fix unsafe cast
  }

  @Override
  public void onCellSelect(int i, int j) {
    throw new NoSuchMethodError("no");
  }
}
