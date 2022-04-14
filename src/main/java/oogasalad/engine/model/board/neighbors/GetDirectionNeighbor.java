package oogasalad.engine.model.board.neighbors;
import java.util.Optional;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.misc.Direction;
import oogasalad.engine.model.board.components.Position;
import oogasalad.engine.model.board.components.PositionState;
import oogasalad.engine.model.board.misc.Ray;

/**
 * Abstract class that allows the board to see what the neighbors are.
 */
public abstract class GetDirectionNeighbor implements GetPositionNeighbor {

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
