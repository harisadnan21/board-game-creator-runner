package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Returns true if piece type at (i, j) is of certain type
 * @author Jake Heller
 */
public class IsPieceType extends Condition {

  private int row;
  private int column;
  private int type;
  private boolean isAbsolute;
  private boolean invert;

  /**
   *
   * @param parameters size 4 array [row, column, type, isAbsolute]
   */
  public IsPieceType(int[] parameters) {
    super(parameters);
    row = getParameter(0);
    column = getParameter(1);
    type = getParameter(2);
    isAbsolute = getParameter(3) != 0;
    invert = getParameter(4) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(row, column);
    if (!isAbsolute) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return invertIfTrue(false, invert);
    }
    return invertIfTrue(isProperPieceType(board, position), invert);
  }

  private boolean isProperPieceType(Board board, Position position) {
    PositionState positionState = board.getPositionStateAt(position);
    return positionState.type() == type;
  }
}
