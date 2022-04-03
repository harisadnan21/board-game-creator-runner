package oogasalad.engine.model.board;

import java.util.Optional;

public class getNorthWestNeighbor extends getDirectionNeighbor {
  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position northwest = new Position(pos.getI() -1, pos.getJ()+1);
    return checkIfValid(board, northwest);
  }


}

