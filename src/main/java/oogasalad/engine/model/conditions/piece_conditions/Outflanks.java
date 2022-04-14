package oogasalad.engine.model.conditions.piece_conditions;

import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.misc.OutOfBoardException;

/**
 * Defines outflanks condition
 *
 * Outflanks returns true if there exists a line of pieces between two points, where the endpoints
 * are owned by one player, and the inner pieces are owned by a different player
 *
 * @author Jake Heller
 */
public class Outflanks extends PieceCondition {

  /**
   *
   * @param parameters
   */
  public Outflanks(int[] parameters) {
    super(parameters);
  }

  @Override
  public boolean isTrue(Board board, int refI, int refJ) throws OutOfBoardException {
    return false;
  }
}
