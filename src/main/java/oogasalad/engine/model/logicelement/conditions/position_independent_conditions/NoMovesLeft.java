package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

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
   * @param referencePoint
   * @return Returns True if no moves left, else returns false
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {

    return false;
  }
}
