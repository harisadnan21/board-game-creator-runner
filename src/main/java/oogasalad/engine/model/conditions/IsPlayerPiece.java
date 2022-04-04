package oogasalad.engine.model.conditions;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

/**
 * Returns true if piece at (i,j) is owned by player k
 * @author Jake Heller
 */
public class IsPlayerPiece extends Condition {

  /**
   *
   * @param parameters size 3 array of [i, j, owner]
   */
  public IsPlayerPiece(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    Optional<Piece> optional = board.getPiece(refI + myParameters[0], refJ + myParameters[1]);
    if (optional.isPresent()) {
      return optional.get().getOwner() == myParameters[2];
    }
    return false;
  }
}
