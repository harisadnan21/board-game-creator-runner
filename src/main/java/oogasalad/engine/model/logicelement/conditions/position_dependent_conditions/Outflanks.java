package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static java.lang.Math.*;
import static oogasalad.engine.model.board.cells.Piece.PLAYER_ONE;

import java.util.function.Supplier;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.utilities.Delta;
import oogasalad.engine.model.logicelement.conditions.Condition;
import org.jooq.lambda.Seq;

/**
 * Defines outflanks condition
 *
 * Outflanks returns true if there exists a line of pieces between two points, where the endpoints
 * are owned by one player, and the inner pieces are owned by a different player
 *
 * @author Alex Bildner, Jake Heller, Ricky Weerts
 */
public class Outflanks extends Condition {


  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private boolean isAbsolute;

  /**
   * Returns true if there exists a line starting from the start position moving in the specified direction
   * where the start position is of a certain player, everything in the middle of the line is of a different player,
   * and the end point is of the same type as the starting player
   *
   * For the purposes of this function direction does not specifically have to move to adjacent cells
   * it can be [2,2] and look at every other cell
   *
   * isAbsolute specifies whether the starting position should add in the reference point or ignore it
   *
   * @param parameters array of size 5 [startRow, startColumn, directionRow, directionColumn, isAbsolute]
   */
  public Outflanks(int[] parameters) {
    super(parameters);
    startRow = parameters[0];
    startColumn = parameters[1];
    rowDirection = parameters[2];
    columnDirection = parameters[3];
    isAbsolute = parameters[4] != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position firstPosition = new Position(startRow, startColumn);
    if (!isAbsolute) {
      firstPosition = transformToRelative(firstPosition, referencePoint);
    }

    Delta delta = new Delta(rowDirection, columnDirection);

    Position positionToCheck = firstPosition;
    int flankSize = -1;
    do {
      if(board.isEmpty(positionToCheck.row(), positionToCheck.column())) {
        return false;
      }
      flankSize++;
      positionToCheck = positionToCheck.add(delta);
    } while(board.isValidPosition(positionToCheck) && !samePlayerAtPositions(board, firstPosition, positionToCheck));

    return flankSize > 0;

  }

  private boolean samePlayerAtPositions(Board board, Position firstPosition, Position secondPosition) {
    return board.getPositionStateAt(firstPosition).player() == board.getPositionStateAt(secondPosition).player();
  }

}
