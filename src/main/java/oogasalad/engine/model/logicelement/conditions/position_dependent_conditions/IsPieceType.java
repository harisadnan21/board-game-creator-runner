package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Returns true if piece type at (i, j) is of certain type
 * @author Jake Heller
 */
public class IsPieceType extends Condition {

  /**
   *
   * @param parameters size 3 array [i, j, type, isAbsolute]
   */
  public IsPieceType(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int i = myParameters[0];
    int j = myParameters[1];
    if (myParameters[3] == 0) {
      i += referencePoint.i();
      j += referencePoint.j();
    }
    if (!board.isValidPosition(i,j)) {
      return false;
    }
    PositionState positionState = board.getPositionStateAt(i,j);
    return positionState.type() == myParameters[2];
  }
}
