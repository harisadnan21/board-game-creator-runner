package oogasalad.engine.model.player;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;

/**
 * Player class
 */
public interface Player {

  /**
   * Engine uses this function to ping player and indicate it
   * should send a move choice
   */
  void chooseMove(Board activeBoard);

  /**
   * Sets the current game board for this player
   * @param board
   */
  void setGameBoard(Board board);

  /**
   * Defines what should happen if the user clicks a cell during
   * this player's turn
   * @param i
   * @param j
   */
  void onCellSelect(int i, int j);

  /**
   * Returns current score for this player
   * score should update after every game
   * @return
   */
  int getScore();

  /**
   * updates score for this player
   * @param change
   */
  void updateScore(int change);

  /**
   * Adds the execute function from the active engine
   *
   * This exists so that the same player can be used for multiple games
   *
   * @param executeMove
   */
  void setExecuteFunction(BiConsumer<Player, Choice> executeMove);
}