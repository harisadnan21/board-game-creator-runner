package oogasalad.engine.model.conditions.piece_conditions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.PositionState;

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
    if (!board.isValidXY(i,j)) {
      return false;
    }
    PositionState positionState = board.getPositionStateAt(i,j);
    return positionState.type() == myParameters[2];
  }
}
