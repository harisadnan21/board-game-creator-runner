package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

/**
 * Sets the turn to player 2
 * @author Robert Cranston
 */
public class Player2Turn implements CheatCode{

  /**
   * executes the cheat code based on the given board. Updates the board through the controller.
   * @param board current game board
   * @param controller controller for the
   * @return  board with the update after the cheat code
   */
  @Override
  public Board accept(Board board, Controller controller) {
    return board.setPlayer(1);
  }
}