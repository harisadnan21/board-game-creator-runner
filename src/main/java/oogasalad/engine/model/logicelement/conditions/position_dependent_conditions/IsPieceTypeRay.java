package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import java.util.Collection;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.board.utilities.Ray;
import oogasalad.engine.model.logicelement.conditions.Condition;

public class IsPieceTypeRay extends Condition {

  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private int length;
  private int type;
  private boolean isAbsolute;
  private boolean invert;

  /**
   *
   * @param parameters size 8 array [startRow, startColumn, rowDirection, columnDirection, length, type, isAbsolute, invert]
   */
  protected IsPieceTypeRay(int[] parameters) {
    super(parameters);
    startRow = getParameter(0);
    startColumn = getParameter(1);
    rowDirection = getParameter(2);
    columnDirection = getParameter(3);
    length = getParameter(4);
    type = getParameter(5);
    isAbsolute = getParameter(6) != 0;
    invert = getParameter(7) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position direction = new Position(rowDirection, columnDirection);
    Position startPosition = new Position(startRow, startColumn);
    if (!isAbsolute) {
      startPosition = transformToRelative(startPosition, referencePoint);
    }
    Collection<PositionState> ray = Ray.getRayOfMaxLength(board, startPosition, direction, length);
    if (ray.size() < length) {
      return invertIfTrue(false, invert);
    }
    for (PositionState cell : ray) {
      if (cell.piece().type() != type) {
        return invertIfTrue(false, invert);
      }
    }
    return invertIfTrue(true, invert);
  }
}
