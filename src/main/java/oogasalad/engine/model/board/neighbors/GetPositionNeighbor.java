package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.components.Position;

@FunctionalInterface
public interface GetPositionNeighbor {
  public Optional<Position> getNeighbor(Position pos, Board board);

}
