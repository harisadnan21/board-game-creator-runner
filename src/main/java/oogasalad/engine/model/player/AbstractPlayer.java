package oogasalad.engine.model.player;

import java.util.function.BiConsumer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;

/**
 * Abstract class that defines a player and has methods that executes a player's turn.
 * @Author Haris Adnan, Jake Heller
 */
public abstract class AbstractPlayer implements Player {

  private Board myCurrentGameBoard;
  private BiConsumer<Player, Choice> myExecuteMove;

  private int myScore;

  protected AbstractPlayer(BiConsumer<Player, Choice> executeMove) {
    myExecuteMove = executeMove;
    myScore = 0;
    myCurrentGameBoard = null;
  }

  /**
   * Sends move choice to Engine
   * 
   * @param player
   * @param choice
   */
  protected void executeMove(Player player, Choice choice) {
    if (myExecuteMove != null) {
      myExecuteMove.accept(player, choice);
    }
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
  public void setGameBoard(Board board) {
    myCurrentGameBoard = board;
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

  @Override
  public void setExecuteFunction(BiConsumer<Player, Choice> executeMove) {
    this.myExecuteMove = executeMove;
  }
}
