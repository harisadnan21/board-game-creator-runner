package oogasalad.engine.model.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;

public class IsOccupied extends Condition {

  /**
   *
   * @param parameters
   */
  public IsOccupied(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    int i = myParameters[0]+ referencePoint.i(); //TODO: why are i & j assigned values but never used, is there a mistake here?
    int j = myParameters[1]+ referencePoint.j();
    if (!board.isValidPosition(i,j)) {
      return false;
    }
    return !board.isEmpty(i, j);
  }
}
