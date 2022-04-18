package oogasalad.engine.model.ai.evaluation.totals;

import java.util.Map;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

public class TotalPieces implements StateEvaluator {

  @Override
  public int evaluate(Board board, int player) {
    StateEvaluator.throwIfInvalid(board);
    Map<Integer, Integer> piecesByPlayer = board.numPiecesByPlayer();
    int evaluation = piecesByPlayer.get(Piece.PLAYER_ONE) - piecesByPlayer.get(Piece.PLAYER_TWO);
    return (player==Piece.PLAYER_ONE) ? evaluation : evaluation * -1;
  }
}
