package oogasalad.engine.model.utilities;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.utilities.Delta;

/**
 * Contains code common to the conditions and actions related to flanking
 *
 * @author Ricky Weerts
 */
public interface Flanking {
  /**
   * Gets the number of pieces flanked
   *
   * @return the number of pieces flanked
   */
  default int getFlankLength(Board board, Position from, Delta delta) {
    Position positionToCheck = from;
    int flankSize = -1;
    do {
      if(board.isEmpty(positionToCheck.row(), positionToCheck.column())) {
        return 0;
      }
      flankSize++;
      positionToCheck = positionToCheck.add(delta);
    } while(board.isValidPosition(positionToCheck) && !samePlayerAtPositions(board, from, positionToCheck));
    return flankSize;
  }

  private boolean samePlayerAtPositions(Board board, Position firstPosition, Position secondPosition) {
    return board.getPositionStateAt(firstPosition).player() == board.getPositionStateAt(secondPosition).player();
  }

}
