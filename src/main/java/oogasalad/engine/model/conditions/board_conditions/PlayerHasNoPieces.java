package oogasalad.engine.model.conditions.board_conditions;

import java.util.stream.Stream;
import javafx.util.Pair;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.jooq.lambda.Seq;

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
    Stream<PositionState> player0 = board.getSatisfyingPositionStatesStream(positionState -> positionState.player()==0);
    Stream<PositionState> player1 = board.getSatisfyingPositionStatesStream(positionState -> positionState.player()==1);
    return Seq.seq(player0).isEmpty() && Seq.seq(player1).isEmpty();
  }
//    int[] players = {0,0};
//    for(PositionState piece : board){
//      if(piece.getValue() != null){
//        players[piece.getValue().getPieceRecord().player()] = players[piece.getValue().getPieceRecord().player()]+1;
//      }
//    }
//    return (players[0] == 0 || players[1]==0);
}
