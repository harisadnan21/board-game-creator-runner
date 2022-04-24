package oogasalad.engine.cheat_codes;

import java.util.Optional;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;


public class RemovePlayer1Piece implements CheatCode{

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
