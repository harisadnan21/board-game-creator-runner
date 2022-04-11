package oogasalad.engine.model.AI;

import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface StateEvaluator {

  public int Evaluate(Board board, int player);

}
