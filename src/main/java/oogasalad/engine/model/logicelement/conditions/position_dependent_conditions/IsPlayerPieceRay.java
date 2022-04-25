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
  /**
   *
   * @param parameters size 6 array [startRow, startColumn, rowDirection, columnDirection, length, player, isAbsolute]
   */
  public IsPlayerPieceRay(int[] parameters) {
    super(parameters);
    startRow = myParameters[0];
    startColumn = myParameters[1];
    rowDirection = myParameters[2];
    columnDirection = myParameters[3];
    length = myParameters[4];
    player = myParameters[5];
    isAbsolute = myParameters[6] != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position direction = new Position(rowDirection, columnDirection);
    Position startPosition = new Position(startRow, startColumn);
    if (!isAbsolute) {
      startPosition = transformToRelative(startPosition, referencePoint);
    }
    Collection<PositionState> ray = getRayOfMaxLength(board, startPosition, direction, length);

    return longEnough(ray) && allForPlayer(ray);
  }

  private boolean longEnough(Collection<PositionState> ray) {
    return ray.size() >= length;
  }

  private boolean allForPlayer(Collection<PositionState> ray) {
    return ray.stream().allMatch(positionState -> positionState.player()==player);
  }
}
