package oogasalad.engine.cheat_codes;

import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;


public class RemoveRandomPlayer1Piece extends CheatCode{

  @Override
  public Board accept(Board board) {
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
