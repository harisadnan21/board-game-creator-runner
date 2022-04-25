package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * returns true if there is a piece of given type on the board
 *
 * @author Jake Heller
 */
public class HasPiece extends BoardCondition {
  private int id;
  private boolean invert;

  /**
   *
   * @param parameters size 2 array [id, invert]
   */
  public HasPiece(int[] parameters) {
    super(parameters);
    id = getParameter(0);
    invert = getParameter(1) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return invertIfTrue(board.getPositionStatesStream().anyMatch(positionState -> positionState.type() == id), invert);
  }
}
