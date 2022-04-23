package oogasalad.engine.model.player;

import java.util.function.BiConsumer;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.board.Board;

/**
 * Abstract class that defines a player and has methods that executes a player's turn.
 * @Author Haris Adnan, Jake Heller
 */
public abstract class Player implements InteractivePlayer {

  private Board myCurrentGameBoard;
  private BiConsumer<Player, Choice> myExecuteMove;

  private int myScore;

  protected Player(BiConsumer<Player, Choice> executeMove) {
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

  public int getScore() {
    return myScore;
  }

  public void updateScore(int change) {
    myScore += change;
  }

  public void setGameBoard(Board board) {
    myCurrentGameBoard = board;
  }

  public void onCellSelect(int i, int j) {

  }
}
