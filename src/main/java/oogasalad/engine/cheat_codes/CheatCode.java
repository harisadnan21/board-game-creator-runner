package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

/**
 * Interface for Cheat codes
 * @author Robert Cranston
 */
public interface CheatCode {

  /**
   * executes the cheat code based on the given board. Updates the board through the controller.
   * @param board current game board
   * @param controller controller for the
   * @return  board with the update after the cheat code
   */
  Board accept(Board board, Controller controller);
}
