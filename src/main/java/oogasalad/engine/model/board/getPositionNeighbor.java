package oogasalad.engine.model.board;

import java.util.Optional;

@FunctionalInterface
public interface getPositionNeighbor {
  public Optional<Position> getNeighbor(Position pos, Board board);

}
