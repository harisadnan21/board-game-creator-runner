package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Position;

@FunctionalInterface
public interface getPositionNeighbor {
  public Optional<Position> getNeighbor(Position pos, ArrayBoard board);

}
