package oogasalad.engine.model.board;

import java.util.Optional;

public class getEastNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position east = new Position(pos.getI()+1, pos.getJ());
    return checkIfValid(board, east);
  }


}
