package oogasalad.engine.model.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;

/**
 * Returns true if piece at (i,j) is owned by player k
 * @author Jake Heller
 */
public class IsPlayerPiece extends Condition {

  /**
   *
   * @param parameters size 3 array of [i, j, owner]
   */
  public IsPlayerPiece(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int i = myParameters[0]+referencePoint.i();
    int j = myParameters[1]+ referencePoint.j();
    if (!board.isValidPosition(i, j)) {
      return false;
    }
    return board.getPositionStateAt(i, j).player() == myParameters[2];
  }
}
