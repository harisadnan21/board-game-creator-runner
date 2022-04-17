package oogasalad.engine.model.player;

/**
 * Player class
 */
public interface PlayerInterface {

  /**
   * Engine uses this function to ping player and indicate it
   * should send a move choice
   */
  void chooseMove();

  /**
   * Defines what should happen if the user clicks a cell during
   * this player's turn
   * @param i
   * @param j
   */
  void onCellSelect(int i, int j);
}