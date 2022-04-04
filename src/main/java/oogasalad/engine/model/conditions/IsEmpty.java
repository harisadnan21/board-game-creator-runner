package oogasalad.engine.model.conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public class IsEmpty extends Condition {

  /**
   * @author Jake Heller
   *
   * @param parameters size 2 array [i, j]
   */
  public IsEmpty(int[] parameters) {
    super(parameters);
  }

  /**
   *
   * @param board
   * @param refI reference i
   * @param refJ reference j
   * @return
   * @throws OutOfBoardException
   */
  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
//    boolean cond = board.getPiece(myParameters[0]+refI, myParameters[1]+refJ).isPresent();
//    System.out.printf("Checking if (%d, %d) is occupied: %b\n", myParameters[0]+refI, myParameters[1]+refJ, cond);

    return !board.getPiece(myParameters[0]+refI, myParameters[1]+refJ).isPresent();
  }

}
