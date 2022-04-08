package oogasalad.engine.model.conditions.piece_conditions;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.PieceRecord;

/**
 * Returns true if piece at (i,j) is owned by player k
 * @author Jake Heller
 */
public class IsPlayerPiece extends PieceCondition {

  /**
   *
   * @param parameters size 3 array of [i, j, owner]
   */
  public IsPlayerPiece(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    int i = myParameters[0]+refI;
    int j = myParameters[1]+refJ;
    if (!board.isValidXY(i, j)) {
      return false;
    }
    return board.getPositionStateAt(i, j).player() == myParameters[2];
  }
}
