package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

@FunctionalInterface
public interface getPositionNeighbor {
  public Optional<Position> getNeighbor(Position pos, Board board);

}
