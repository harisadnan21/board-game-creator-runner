package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

public class Remove extends Action {

  /**
   *
   * @param parameters size 2 array [i, j, isAbsolute]
   */
  public Remove(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    Position position = new Position(myParameters[0], myParameters[1]);
    if (myParameters[2] == 0) {
      position = transformToRelative(position, referencePoint);
    }
    return board.remove(position.row(), position.column());
  }
}
