package oogasalad.engine.model;

import java.util.Optional;

public class getSouthNeighbor implements getPositionNeighbor{
    public Optional<Position> getNeighbor(Position pos, Board board) {
      Position south = new Position(pos.getI(), pos.getJ()-1);
      if(!board.isValid(south)){
        return Optional.empty();
      }
      return Optional.of(south);
    }

}
