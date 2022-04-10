package oogasalad.engine.model.conditions.board_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.PositionState;

/**
 * Condition that evaluates to true when either player has no more pieces on the board
 * @author Robert Cranston
 */
public class PlayerHasNoPieces implements BoardCondition{

  /**
   * Counts the number of pieces each player has and returns true if a player has no pieces.
   * @param board current board state
   * @return true if a player has no pieces left
   */
  @Override
  public boolean isTrue(Board board) {
    int[] players = {0,0};
    for(PositionState cell : board){
      Piece piece = cell.piece();
      if(!cell.isEmpty()){
        players[piece.player()] = players[piece.player()]+1;
      }
    }
    return (players[0] == 0 || players[1]==0);
  }
}
