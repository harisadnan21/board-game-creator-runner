package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class Translate extends Action {

  /**
   *
   * @param parameters should be size 4 array where
   * [i1, j1, i2, j2]
   */
  public Translate(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, Position referencePoint) {
    int row1 = myParameters[0] + referencePoint.i();
    int column1 = myParameters[1] + referencePoint.j();
    int row2 = myParameters[2] + referencePoint.i();
    int column2 = myParameters[3] + referencePoint.j();
    return board.move(row1, column1, row2, column2);
  }

}
