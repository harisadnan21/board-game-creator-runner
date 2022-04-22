package oogasalad.engine.model.player;

import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.rule.Move;

/**
 * Player class
 */
public interface PlayerInterface {

  /**
   * Engine uses this function to ping player and indicate it
   * should send a move choice
   *
   * @param lastChoice the previous choice made
   */
  void chooseMove(Choice lastChoice);

  /**
   * Defines what should happen if the user clicks a cell during
   * this player's turn
   * @param i
   * @param j
   */
  void onCellSelect(int i, int j);
}