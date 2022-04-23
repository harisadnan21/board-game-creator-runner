package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Returns true if piece at (i,j) is owned by player k
 * @author Jake Heller
 */
public class IsPlayerPiece extends Condition {

  private int row;
  private int column;
  private int player;
  private boolean isAbsolute;
  /**
   *
   * @param parameters size 3 array of [row, column, owner, isAbsolute]
   */
  public IsPlayerPiece(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    player = getParameter(2);
    isAbsolute = getParameter(3) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return board.getPositionStateAt(position).player() == player;
  }
}
