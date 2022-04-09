package oogasalad.engine.model.conditions.piece_conditions;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

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
    if (!board.isValidPosition(i, j)) {
      return false;
    }
    Optional<Piece> optional = board.getPieceRecord(i, j);
    if (optional.isPresent()) {
      return optional.get().player() == myParameters[2];
    }
    return false;
  }
}
