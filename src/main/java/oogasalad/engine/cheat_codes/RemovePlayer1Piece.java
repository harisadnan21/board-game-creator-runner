package oogasalad.engine.cheat_codes;

import java.util.Optional;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;

/**
 * Removes a piece from player one's pieces
 * @author Robert Cranston
 */
public class RemovePlayer1Piece implements CheatCode{

  /**
   * executes the cheat code based on the given board. Updates the board through the controller.
   * @param board current game board
   * @param controller controller for the
   * @return  board with the update after the cheat code
   */
  @Override
  public Board accept(Board board, Controller controller) {
    Optional<Position> pos = (board.getPositionStatesStream()
        .filter(e -> e.player() == 1)
        .findAny()
        .map(PositionState::position));
    if(pos.isPresent()){
      return board.removePiece(pos.get());
    }
     return board;
  }



}
