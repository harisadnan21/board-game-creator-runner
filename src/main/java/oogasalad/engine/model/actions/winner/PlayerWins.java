package oogasalad.engine.model.actions.winner;

import oogasalad.engine.model.board.Board;

/**
 * Returns a predefined player as the winner
 *
 * @author Jake Heller
 */
public class PlayerWins extends AbstractWinDecision {

  /**
   *
   * @param parameters size 1 where [player] is the player returned
   */
  public PlayerWins(int[] parameters) {
    super(parameters);
  }

  @Override
  public int decideWinner(Board board) {
    return 0;
  }
}
