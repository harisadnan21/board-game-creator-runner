package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.utilities.Delta;
import oogasalad.engine.model.utilities.Flanking;

/**
 * Flips any pieces being outflanked to be the same as the source
 *
 * @author Alex Bildner, Ricky Weerts
 */
public class Outflank extends Action implements Flanking {
  private int startRow;
  private int startColumn;
  private int rowDirection;
  private int columnDirection;
  private boolean isAbsolute;

  /**
   *
   * Almost identical requirements to condition, except if
   * there is an outflanking in this direction
   * flip everything in between to the type and player of the start position
   *
   * should do nothing if there is no outflanking in this direction
   *
   * isAbsolute specifies if the source position adds in the reference point or not
   *
   * @param parameters size - x array [startRow, startColumn, directionRow, directionColumn, isAbsolute]
   */
  public Outflank(int[] parameters) {
    super(parameters);
    startRow = parameters[0];
    startColumn = parameters[1];
    rowDirection = parameters[2];
    columnDirection = parameters[3];
    isAbsolute = parameters[4] != 0;
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position start = new Position(startRow, startColumn);
    Delta delta = new Delta(rowDirection, columnDirection);
    if (!isAbsolute) {
      start = transformToRelative(start, referencePoint);
    }
    int flankSize = getFlankLength(board, start, delta);

    Position flankedPosition = start.add(delta);
    Piece replacement = board.getPositionStateAt(start).piece();
    for(int i = 0; i < flankSize; i++, flankedPosition = flankedPosition.add(delta)) {
      board = board.placeNewPiece(flankedPosition.row(), flankedPosition.column(), replacement.type(), replacement.player());
    }

    return board;
  }
}
