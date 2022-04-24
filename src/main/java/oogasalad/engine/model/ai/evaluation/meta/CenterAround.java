package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;

/**
 * @author Alex Bildner
 */
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
  public int regularizeScore(int oldScore) {
    return -1 * Math.abs(center - oldScore);
  }

  @Override
  public Evaluation evaluate(Board board) {
    Evaluation evaluation = stateEvaluator.evaluate(board);
    int oldPlayerOneScore = evaluation.forPlayer(Piece.PLAYER_ONE);
    int oldPlayerTwoScore = evaluation.forPlayer(Piece.PLAYER_TWO);
    int newPlayerOneScore = centerPlayerOne ? regularizeScore(oldPlayerOneScore) : oldPlayerOneScore;
    int newPlayerTwoScore = centerPlayerTwo ? regularizeScore(oldPlayerTwoScore) : oldPlayerTwoScore;
    return new Evaluation(newPlayerOneScore, newPlayerTwoScore);
  }
}
