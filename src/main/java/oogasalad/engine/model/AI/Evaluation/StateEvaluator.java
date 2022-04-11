package oogasalad.engine.model.AI.Evaluation;

import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface StateEvaluator {

  public int Evaluate(Board board, int player);

}
