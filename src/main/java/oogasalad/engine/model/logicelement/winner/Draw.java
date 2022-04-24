package oogasalad.engine.model.logicelement.winner;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;

/**
 * Should always return the NO_PLAYER int value to indicate a draw
 * @author Jake Heller
 */
public class Draw extends AbstractWinDecision {

  /**
   *
   * @param parameters unused
   */
  public Draw(int[] parameters) {
    super(parameters);
  }

  @Override
  public int decideWinner(Board board) {
    return Piece.NO_PLAYER;
  }
}
