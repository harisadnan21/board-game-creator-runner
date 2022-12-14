package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static oogasalad.engine.model.board.utilities.Ray.getRayOfMaxLength;

import java.util.Collection;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.logicelement.conditions.Condition;

public class IsPlayerPieceRay extends Condition {

  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private int length;
  private int player;
  private boolean isAbsolute;
  private boolean invert;

  /**
   * @param parameters size 8 array [startRow, startColumn, rowDirection, columnDirection, length, player, isAbsolute, invert]
   * @author Alex Bildner, Jake Heller
   */
  public IsPlayerPieceRay(int[] parameters) {
    super(parameters);
    startRow = getParameter(0);
    startColumn = getParameter(1);
    rowDirection = getParameter(2);
    columnDirection = getParameter(3);
    length = getParameter(4);
    player = getParameter(5);
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
    Collection<PositionState> ray = getRayOfMaxLength(board, startPosition, direction, length);

    return invertIfTrue(longEnough(ray) && allForPlayer(ray), invert);
  }

  private boolean longEnough(Collection<PositionState> ray) {
    return ray.size() >= length;
  }

  private boolean allForPlayer(Collection<PositionState> ray) {
    return ray.stream().allMatch(positionState -> positionState.player() == player);
  }
}
