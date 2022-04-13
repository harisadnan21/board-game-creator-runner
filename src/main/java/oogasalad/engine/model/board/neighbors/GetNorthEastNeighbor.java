package oogasalad.engine.model.board.neighbors;

import oogasalad.engine.model.board.Direction;

public class GetNorthEastNeighbor extends GetDirectionNeighbor {

  @Override
  Direction getDirection() {
    return Direction.NORTHEAST;
  }

}
