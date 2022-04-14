package oogasalad.engine.model.board.neighbors;

import oogasalad.engine.model.board.misc.Direction;

public class GetWestNeighbor extends GetDirectionNeighbor {
  @Override
  Direction getDirection() {
    return Direction.WEST;
  }

}
