package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Returns true if piece at (i,j) is owned by player k
 * @author Jake Heller
 */
public class IsPlayerPiece extends Condition {

  /**
   *
   * @param parameters size 3 array of [i, j, owner, isAbsolute]
   */
  public IsPlayerPiece(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(myParameters[0], myParameters[1]);
    if (myParameters[3] == 0) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return board.getPositionStateAt(position).player() == myParameters[2];
  }
}
