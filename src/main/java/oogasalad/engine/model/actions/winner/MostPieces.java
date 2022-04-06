package oogasalad.engine.model.actions.winner;

import javafx.util.Pair;
import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;

public class MostPieces implements Winner {

  public MostPieces(){}

  @Override
  public int decideWinner(Board board) {
    int[] players = new int[] {0,0};
    for(Pair<Position, Piece> piece : board){
      if(piece.getValue()!= null) {
        players[piece.getValue().getPieceRecord().player()] = players[piece.getValue().getPieceRecord().player()]+1;
      }
    }
    if(players[0] == players[1]) return -1;
    else return Winner.maxValueIndex(players);
  }


}
