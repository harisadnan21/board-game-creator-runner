package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class getEastNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position east = new Position(pos.getI()+1, pos.getJ());
    return checkIfValid(board, east);
  }


}
