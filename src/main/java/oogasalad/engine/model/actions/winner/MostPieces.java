package oogasalad.engine.model.actions.winner;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.PositionState;

/**
 * Class that decides winner based on which player has more pieces currently on the board.
 * @author Robert Cranston
 */
public class MostPieces implements Winner {

  /**
   * Counts how many pieces both players currently have on the board and returns the one with the highest amount
   * @param board current board state
   * @return int representation of player with the most pieces
   */
  @Override
  public int decideWinner(Board board) {
    int[] players = new int[] {0,0};
    for(PositionState cell : board){
      Piece piece = cell.piece();
      if(piece != null) {
        players[piece.player()] = players[piece.player()]+1;
      }
    }
    if(players[0] == players[1]) return -1;
    else return Winner.maxValueIndex(players);
  }


}
