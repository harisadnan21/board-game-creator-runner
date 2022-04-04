package oogasalad.engine.model.board.neighbors;
import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public abstract class getDirectionNeighbor implements getPositionNeighbor{
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Position nextPos = new Position(pos.getI() + 1 , pos.getJ() + 1);
    return checkIfValid(board, nextPos);
  }
  public Optional<Position> checkIfValid(Board board, Position newPosition){
    if(!board.isValid(newPosition)){
      return Optional.empty();
    }
    return Optional.of(newPosition);
  }

}
