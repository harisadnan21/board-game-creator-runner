package oogasalad.engine.model.board;

import java.util.Optional;

public class getSouthNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position south = new Position(pos.getI(), pos.getJ()-1);
    return checkIfValid(board, south);
  }
}
