package oogasalad.engine.model.conditions;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

/**
 * Returns true if piece type at (i, j) is of certain type
 * @author Jake Heller
 */
public class IsPieceType extends Condition {

  /**
   *
   * @param parameters size 3 array [i, j, type]
   */
  public IsPieceType(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    Optional<Piece> optional = board.getPiece(refI + myParameters[0], refJ + myParameters[1]);
    if (optional.isPresent()) {
      return optional.get().getType() == myParameters[2];
    }
    return false;
  }
}
