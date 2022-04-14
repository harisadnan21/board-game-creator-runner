package oogasalad.engine.model.conditions.piece_conditions;

import oogasalad.engine.model.board.misc.OutOfBoardException;
import oogasalad.engine.model.board.boards.Board;

/**
 * @author Jake Heller
 */
public class IsEmpty extends PieceCondition {

  /**
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

    int i = myParameters[0]+refI;
    int j = myParameters[1]+refJ;
    if (!board.isValidPosition(i, j)) {
      return false;
    }
    return board.isEmpty(i, j);
  }

}
