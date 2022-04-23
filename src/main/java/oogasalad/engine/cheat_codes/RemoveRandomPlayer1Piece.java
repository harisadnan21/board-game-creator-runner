package oogasalad.engine.cheat_codes;

import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;


public class RemoveRandomPlayer1Piece extends CheatCode{

  @Override
  public Board accept(Board board) {
    Optional<Position> pos = (board.getPositionStatesStream()
        .filter(e -> e.player() == 1)
        .findFirst().map(PositionState::position));
    if(pos.isPresent()){
      System.out.println("here");
      return board.removePiece(pos.get());
    }
     return board;
  }



}
