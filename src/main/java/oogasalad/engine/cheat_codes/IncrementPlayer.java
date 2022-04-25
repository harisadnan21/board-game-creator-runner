package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

/**
 * Moves the turn to the next player
 * @author Robert Cranston
 */
public class IncrementPlayer implements CheatCode{
  /**
   * increases the player based on the given board. Updates the board through the controller.
   * @param board current game board
   * @param controller controller for the
   * @return  board with the update after the cheat code
   */
  @Override
  public Board accept(Board board, Controller controller) {
    return controller.getEngine().incrementPlayer(board);
  }
}