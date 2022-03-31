package oogasalad.engine.model.board;

import java.util.Optional;

public class getNorthNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position north = new Position(pos.getI(), pos.getJ()+1);
    return checkIfValid(board, north);
  }


}
