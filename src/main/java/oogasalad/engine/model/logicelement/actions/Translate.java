package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class Translate extends Action {

  private int startRow;
  private int startColumn;
  private int endRow;
  private int endColumn;
  private boolean isAbsolute;
  /**
   *
   * @param parameters should be size 4 array where
   * [startRow, startColumn, endRow, endColumn, isAbsolute]
   */
  public Translate(int[] parameters) {
    super(parameters);
    startRow = myParameters[0];
    startColumn = myParameters[1];
    endRow = myParameters[2];
    endColumn = myParameters[3];
    isAbsolute = myParameters[4] != 0;
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position startPosition = new Position(startRow, startColumn);
    Position endPosition = new Position(endRow, endColumn);
    if (!isAbsolute) {
      startPosition = transformToRelative(startPosition, referencePoint);
      endPosition = transformToRelative(endPosition, referencePoint);
    }
    return board.move(startPosition.row(), startPosition.column(), endPosition.row(), endPosition.column());
  }

}
