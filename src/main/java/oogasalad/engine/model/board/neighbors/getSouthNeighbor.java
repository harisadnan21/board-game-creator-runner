package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Direction;
import oogasalad.engine.model.board.Position;

public class getSouthNeighbor extends getDirectionNeighbor {

  @Override
  Direction getDirection() {
    return Direction.SOUTH;
  }
}
