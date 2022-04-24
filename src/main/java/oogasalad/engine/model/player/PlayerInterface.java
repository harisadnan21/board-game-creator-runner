package oogasalad.engine.model.player;

import oogasalad.engine.model.board.Board;

/**
 * Player class
 */
public interface PlayerInterface {

  /**
   * Engine uses this function to ping player and indicate it
   * should send a move choice
   */
  void chooseMove(Board activeBoard);

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
}