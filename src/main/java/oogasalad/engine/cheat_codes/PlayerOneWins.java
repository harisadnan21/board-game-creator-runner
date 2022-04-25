package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

/**
 * Sets player one to the winner and ends the game
 * @author Robert Cranston
 */
public class PlayerOneWins implements CheatCode{

  /**
   * executes the cheat code based on the given board. Updates the board through the controller.
   * @param board current game board
   * @param controller controller for the
   * @return  board with the update after the cheat code
   */
  @Override
  public Board accept(Board board, Controller controller) {
    controller.getEngine().endGame(0);
    return controller.getInitialBoard();
  }
}
