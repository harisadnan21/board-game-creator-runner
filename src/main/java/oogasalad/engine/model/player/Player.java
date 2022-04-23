package oogasalad.engine.model.player;

import java.util.function.BiConsumer;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.Game;

/**
 * Abstract class that defines a player and has methods that executes a player's turn.
 * @Author Haris Adnan, Jake Heller
 */
public abstract class Player implements PlayerInterface {

  private Oracle oracle;
  private Board myCurrentGameBoard;
  private BiConsumer<Player, Choice> myExecuteMove;

  private int myScore;

  protected Player(Oracle oracle, BiConsumer<Player, Choice> executeMove) {
    this.oracle = oracle;
    myExecuteMove = executeMove;
    myScore = 0;
    myCurrentGameBoard = null;
  }

  protected Oracle getOracle() {
    return oracle;
  }

  /**
   * Sends move choice to Engine
   * 
   * @param player
   * @param choice
   */
  protected void executeMove(Player player, Choice choice) {
    myExecuteMove.accept(player, choice);
  }

  /**
   * Returns the current game board
   * Players should not be able to set the current game board
   * @return
   */
  protected Board getGameBoard() {
    return myCurrentGameBoard;
  }

  @Override
  public int getScore() {
    return myScore;
  }

  @Override
  public void updateScore(int change) {
    myScore += change;
  }

  @Override
  public void chooseMove(Board board) {
    myCurrentGameBoard = board;
  }
}
