package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;

public class CenterAround implements Regularizes, StateEvaluator {
  private int center;
  private boolean centerPlayerOne;
  private boolean centerPlayerTwo;
  private StateEvaluator stateEvaluator;

  public CenterAround(StateEvaluator stateEvaluator, boolean centerPlayerOne, boolean centerPlayerTwo, int center) {
    this.center = center;
    this.centerPlayerOne = centerPlayerOne;
    this.centerPlayerTwo = centerPlayerTwo;
    this.stateEvaluator = stateEvaluator;
  }

  @Override
  public int regularizeEvaluationScore(StateEvaluator stateEvaluator, Board board, int player) {
    int originalScore = stateEvaluator.evaluate(board, player);
    return -1 * Math.abs(center - originalScore);
  }

  @Override
  public int evaluate(Board board, int player) {
    if(player== Piece.PLAYER_ONE) {
      return centerPlayerOne? regularizeEvaluationScore(stateEvaluator, board, player) : stateEvaluator.evaluate(board, player);
    }
    return centerPlayerTwo? regularizeEvaluationScore(stateEvaluator, board, player) : stateEvaluator.evaluate(board, player);
  }
}
