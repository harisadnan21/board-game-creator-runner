package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Defines outflanks condition
 *
 * Outflanks returns true if there exists a line of pieces between two points, where the endpoints
 * are owned by one player, and the inner pieces are owned by a different player
 *
 * @author Jake Heller
 */
public class Outflanks extends Condition {

  /**
   *
   * @param parameters
   */
  public Outflanks(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return false;
  }

}
