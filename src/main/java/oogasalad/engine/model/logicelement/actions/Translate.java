package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class Translate extends Action {

  /**
   *
   * @param parameters should be size 4 array where
   * [i1, j1, i2, j2, isAbsolute]
   */
  public Translate(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position startPosition = new Position(myParameters[0], myParameters[1]);
    Position endPosition = new Position(myParameters[2], myParameters[3]);
    if (myParameters[4] == 0) {
      startPosition = transformToRelative(startPosition, referencePoint);
      endPosition = transformToRelative(endPosition, referencePoint);
    }
    return board.move(startPosition.row(), startPosition.column(), endPosition.row(), endPosition.column());
  }

}
