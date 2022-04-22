package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * @author Jake Heller
 */
public class IsEmpty extends Condition {

  /**
   * isAbsolute is 0 for relative, 1 for absolute
   * @param parameters size 3 array [i, j, isAbsolute]
   *
   */
  public IsEmpty(int[] parameters) {
    super(parameters);
  }


  /**
   *
   * @param board
   * @param referencePoint reference point for this condition
   * @return
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int i = myParameters[0];
    int j = myParameters[1];
    if (myParameters[2] == 0) {
      i += referencePoint.i();
      j += referencePoint.j();
    }
    if (!board.isValidPositionCoordinates(i, j)) {
      return false;
    }
    return board.isEmpty(i, j);
  }

}
