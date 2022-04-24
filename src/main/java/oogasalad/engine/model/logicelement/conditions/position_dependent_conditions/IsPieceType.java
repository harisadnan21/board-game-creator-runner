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

  /**
   *
   * @param parameters size 3 array [i, j, type, isAbsolute]
   */
  public IsPieceType(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position position = new Position(myParameters[0], myParameters[1]);
    if (myParameters[3] == 0) {
      position = transformToRelative(position, referencePoint);
    }
    if (!board.isValidPosition(position)) {
      return false;
    }
    return isProperPieceType(board, position);
  }

  private boolean isProperPieceType(Board board, Position position) {
    PositionState positionState = board.getPositionStateAt(position);
    return positionState.type() == myParameters[2];
  }
}
