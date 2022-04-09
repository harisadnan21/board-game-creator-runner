package oogasalad.engine.model.conditions.piece_conditions;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

/**
 * Returns true if piece type at (i, j) is of certain type
 * @author Jake Heller
 */
public class IsPieceType extends PieceCondition {

  /**
   *
   * @param parameters size 3 array [i, j, type]
   */
  public IsPieceType(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    int i = myParameters[0]+refI;
    int j = myParameters[1]+refJ;
    if (!board.isValidPosition(i, j)) {
      return false;
    }
    Optional<Piece> optional = board.getPiece(i, j);
    if (optional.isPresent()) {
      return optional.get().type()== myParameters[2];
    }
    return false;
  }
}
