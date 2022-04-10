package oogasalad.engine.model.conditions.board_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.PositionState;

/**
 * Condition that evaluates to true when the entire board is full of pieces
 * @author Robert Cranston
 */
public class BoardFull implements BoardCondition{

  /**
   * Checks every board cell and returns true if every cell has a piece
   * @param board current board state
   * @return true if the board has no empty spaces
   */
  @Override
  public boolean isTrue(Board board){
    for(PositionState cell : board){
      if(cell.isPresent()){
        return false;
      }
    }
    return true;
  }
}
