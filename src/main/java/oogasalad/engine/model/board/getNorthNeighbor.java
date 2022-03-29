package oogasalad.engine.model.board;

import java.util.Optional;

public class getNorthNeighbor implements getPositionNeighbor {

  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position north = new Position(pos.getI(), pos.getJ()+1);
    if(!board.isValid(north)){
      return Optional.empty();
    }
    return Optional.of(north);
  }


}
