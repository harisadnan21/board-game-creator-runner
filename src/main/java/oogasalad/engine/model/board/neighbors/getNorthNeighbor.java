package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Position;

public class getNorthNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, ArrayBoard board) {
    Position north = new Position(pos.getI(), pos.getJ()+1);
    return checkIfValid(board, north);
  }


}
