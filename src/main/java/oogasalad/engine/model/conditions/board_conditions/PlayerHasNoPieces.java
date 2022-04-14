package oogasalad.engine.model.conditions.board_conditions;

import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.boards.BoardUtils;
import oogasalad.engine.model.board.components.PositionState;
import org.jooq.lambda.Seq;

/**
 * Condition that evaluates to true when either player has no more pieces on the board
 * @author Robert Cranston
 */
public class PlayerHasNoPieces extends BoardCondition{

  public PlayerHasNoPieces(int[] parameters){
    super(parameters);
  }

  /**
   * Counts the number of pieces each player has and returns true if a player has no pieces.
   * @param board current board state
   * @return true if a player has no pieces left
   */
  @Override
  public boolean isTrue(Board board) {
    Seq<PositionState> player0 = BoardUtils.getSatisfyingPositionStatesSeq(board,
        posState -> posState.player()==0);
    Seq<PositionState> player1 = BoardUtils.getSatisfyingPositionStatesSeq(board,
        posState -> posState.player()==1);
    return player0.isEmpty() || player1.isEmpty();
  }
//    int[] players = {0,0};
//    for(PositionState piece : board){
//      if(piece.getValue() != null){
//        players[piece.getValue().getPieceRecord().player()] = players[piece.getValue().getPieceRecord().player()]+1;
//      }
//    }
//    return (players[0] == 0 || players[1]==0);
}
