package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Position;

public class getSouthEastNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, ArrayBoard board) {
    Position southeast = new Position(pos.getI() + 1, pos.getJ()-1);
    return checkIfValid(board, southeast);
  }


}

