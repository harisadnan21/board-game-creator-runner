package oogasalad.engine.model.ai.evaluation.constant;

import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class Constant implements StateEvaluator {
  private final int constantEval;

  public Constant(final int n) {
    constantEval = n;
  }

  @Override
  public Evaluation evaluate(Board board) {
    return new Evaluation(constantEval, constantEval);
  }
}
