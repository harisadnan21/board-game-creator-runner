package oogasalad.engine.model.AI.Evaluation;

import java.util.Map;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

public class TotalPieces implements StateEvaluator {

  @Override
  public int Evaluate(Board board, int player) {
    Map<Integer, Integer> piecesByPlayer = board.numPiecesByPlayer();
    int difference = piecesByPlayer.get(Piece.PLAYER_ONE) - piecesByPlayer.get(Piece.PLAYER_TWO);
    int evaluation = difference;

    if(player == Piece.PLAYER_TWO) {
      evaluation = -1 * evaluation;
    }
    return evaluation;
  }
}
