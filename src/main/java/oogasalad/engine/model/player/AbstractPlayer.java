package oogasalad.engine.model.player;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;

/**
 * Abstract class that defines a player and has methods that executes a player's turn.
 * @Author Haris Adnan, Jake Heller
 */
public abstract class AbstractPlayer implements Player {

  private Oracle myOracle;
  private Board myCurrentGameBoard;
  private BiConsumer<Player, Choice> myExecuteMove;

  private int myScore;

  protected AbstractPlayer(Oracle oracle, BiConsumer<Player, Choice> executeMove) {
    myOracle = oracle;
    myExecuteMove = executeMove;
    myScore = 0;
    myCurrentGameBoard = null;
  }

  protected Oracle getOracle() {
    return myOracle;
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

  /**
   * Sets the current game board for this player
   * @param board
   */
  protected void setGameBoard(Board board) {
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
  public void addDependencies(Oracle oracle, BiConsumer<Player, Choice> executeMove,
      Consumer<Set<Position>> setValidMarks) {
    myOracle = oracle;
    myExecuteMove = executeMove;
  }

  /**
   * Returns valid choices as a set
   * empty set if oracle is null
   * @return
   */
  protected Set<Choice> getMoves() {
    if (myOracle != null) {
      return myOracle.getValidChoices(myCurrentGameBoard).collect(Collectors.toSet());
    }
    else {
      return new HashSet<>();
    }
  }
}
