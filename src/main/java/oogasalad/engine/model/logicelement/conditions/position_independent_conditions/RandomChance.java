package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Returns true with probability 1/n
 *
 * @author Jake Heller
 */
public class RandomChance extends BoardCondition {
  
  protected RandomChance(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return false;
  }
}
