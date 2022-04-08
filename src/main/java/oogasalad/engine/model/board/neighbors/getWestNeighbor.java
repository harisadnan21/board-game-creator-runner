package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Position;

public class getWestNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, ArrayBoard board) {
    Position west = new Position(pos.getI() - 1, pos.getJ());
    return checkIfValid(board, west);
  }


}
