package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

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
    int row = myParameters[0] + referencePoint.i();
    int column = myParameters[1] + referencePoint.j();

    return board.remove(row, column);
  }
}
