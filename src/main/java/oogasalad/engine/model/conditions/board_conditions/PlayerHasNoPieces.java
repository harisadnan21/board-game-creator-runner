package oogasalad.engine.model.conditions.board_conditions;

import javafx.util.Pair;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;

public class PlayerHasNoPieces implements BoardCondition{

  /**
   * @param board
   * @throws OutOfBoardException
   */
  @Override
  public boolean isTrue(Board board) throws OutOfBoardException {
    int[] players = {0,0};
    for(Pair<Position, Piece> piece : board){
      if(piece.getValue() != null){
        players[piece.getValue().getPieceRecord().player()] = players[piece.getValue().getPieceRecord().player()]+1;
      }
    }

    return (players[0] == 0 || players[1]==0);
  }
}
