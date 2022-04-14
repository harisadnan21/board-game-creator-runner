package oogasalad.engine.model.conditions.board_conditions;

import oogasalad.engine.model.board.boards.Board;

/**
 * Class that evaluates if the game is at a standstill when no player has any available moves left
 * to perform.(leading the game to end in a draw)
 * @author Haris Adnan
 */
public class NoMovesLeft extends BoardCondition{

  public NoMovesLeft(int[] parameters){
    super(parameters);
  }
  /**
   *
   * @param board current board state
   * @return Returns True if no moves left, else returns false
   */
  @Override
  public boolean isTrue(Board board) {
//    if (board.getValidMoves() == null){
//      return true;
//    }
//    return false;
    return board.getValidMoves() == null;
  }
}
