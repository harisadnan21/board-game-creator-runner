package oogasalad.engine.model.AI.Evaluation;

import java.util.Map;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

public class TotalPieces implements StateEvaluator {

  @Override //TODO: implement this
  public int Evaluate(Board board, int player) {
    Map<Integer, Integer> piecesByPlayer = board.numPiecesByPlayer();
    int evaluation = 0;
    if(player == Piece.PLAYER_ONE) {
      
    } else {
      
    }
    return evaluation;
  }
}
