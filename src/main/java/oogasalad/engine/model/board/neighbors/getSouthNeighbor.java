package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Position;

public class getSouthNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, ArrayBoard board) {
    Position south = new Position(pos.getI(), pos.getJ()-1);
    return checkIfValid(board, south);
  }
}
