package oogasalad.engine.model.player;

import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.Game;

/**
 * Abstract class that defines a player and has methods that executes a player's turn.
 * @Author Haris Adnan
 */

public abstract class Player implements PlayerInterface {

  private Oracle oracle;
  private Game myGame;

  protected Player(Oracle oracle, Game game) {
    this.oracle = oracle;
    myGame = game;
  }

  @Override
  public abstract Choice chooseMove();

  public boolean isMyTurn() {
    return false;
  }

  protected Oracle getOracle() {
    return oracle;
  }

  /**
   * Returns the current game board
   * Players should not be able to set the current game board
   * @return
   */
  protected Board getGameBoard() {
    return myGame.getBoard();
  }
}
