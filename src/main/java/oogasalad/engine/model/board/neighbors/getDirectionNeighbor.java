package oogasalad.engine.model.board.neighbors;
import java.util.Optional;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Direction;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.board.Ray;

/**
 * Abstract class that allows the board to see what the neighbors are.
 */
public abstract class getDirectionNeighbor implements getPositionNeighbor{

  @Override
  public Optional<Position> getNeighbor(Position pos, Board board) {
    Direction direction = this.getDirection();
    Optional<PositionState> positionState = Ray.getDirectionalRay(board, pos, direction).skip(1).findFirst();
    if(positionState.isEmpty()){
      return Optional.empty();
    }
    return Optional.of(positionState.get().position());
  }

  abstract Direction getDirection();

}
