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
import oogasalad.engine.model.utilities.Flanking;
import org.jooq.lambda.Seq;

/**
 * Defines outflanks condition
 *
 * Outflanks returns true if there exists a line of pieces between two points, where the endpoints
 * are owned by one player, and the inner pieces are owned by a different player
 *
 * @author Alex Bildner, Jake Heller, Ricky Weerts
 */
public class Outflanks extends Condition implements Flanking {
  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private boolean isAbsolute;
  private boolean invert;

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
   * @param parameters array of size 6 [startRow, startColumn, directionRow, directionColumn, isAbsolute, invert]
   */
  public Outflanks(int[] parameters) {
    super(parameters);
    startRow = getParameter(0);
    startColumn = getParameter(1);
    rowDirection = getParameter(2);
    columnDirection = getParameter(3);
    isAbsolute = getParameter(4) != 0;
    invert = getParameter(5) != 0;
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    Position start = new Position(startRow, startColumn);
    if (!isAbsolute) {
      start = transformToRelative(start, referencePoint);
    }
    return invertIfTrue(getFlankLength(board, start, new Delta(rowDirection, columnDirection)) > 0, invert);
  }

}
