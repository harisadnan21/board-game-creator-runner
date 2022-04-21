package oogasalad.engine.model.ai.evaluation.totals;

import java.util.Map;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

public class TotalPieces implements StateEvaluator {

  @Override
  public Evaluation evaluate(Board board) {
    StateEvaluator.throwIfInvalid(board);
    Map<Integer, Integer> piecesByPlayer = board.numPiecesByPlayer();
    int evaluation = piecesByPlayer.get(Piece.PLAYER_ONE) - piecesByPlayer.get(Piece.PLAYER_TWO);
    return new Evaluation(evaluation, -1 * evaluation);
  }
}
