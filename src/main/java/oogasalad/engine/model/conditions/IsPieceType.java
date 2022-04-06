package oogasalad.engine.model.conditions;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.PieceRecord;

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
    int i = myParameters[0]+refI;
    int j = myParameters[1]+refJ;
    if (!board.isValid(i, j)) {
      return false;
    }
    Optional<PieceRecord> optional = board.getPieceRecord(i, j);
    if (optional.isPresent()) {
      return optional.get().type()== myParameters[2];
    }
    return false;
  }
}
