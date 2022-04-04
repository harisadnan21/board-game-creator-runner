package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class getSouthWestNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position southeast = new Position(pos.getI() - 1, pos.getJ()-1);
    return checkIfValid(board, southeast);
  }
}

