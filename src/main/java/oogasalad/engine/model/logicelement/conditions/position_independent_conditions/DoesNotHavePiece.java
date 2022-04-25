package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * returns true if there is no piece of given type on the board
 *
 * @author Jake Heller
 */
public class DoesNotHavePiece extends BoardCondition {

  private int id;
  /**
   *
   * @param parameters size 1 array [id]
   */
  public DoesNotHavePiece(int[] parameters) {
    super(parameters);
    id = parameters[0];
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return board.getPositionStatesStream().filter(positionState -> positionState.type() == id).count() == 0;
  }
}
