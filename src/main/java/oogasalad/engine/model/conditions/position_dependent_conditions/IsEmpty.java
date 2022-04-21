package oogasalad.engine.model.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;

/**
 * @author Jake Heller
 */
public class IsEmpty extends Condition {

  /**
   * @param parameters size 2 array [i, j]
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
    int i = myParameters[0]+referencePoint.i();
    int j = myParameters[1]+referencePoint.j();
    if (!board.isValidPosition(i, j)) {
      return false;
    }
    return board.isEmpty(i, j);
  }

}
