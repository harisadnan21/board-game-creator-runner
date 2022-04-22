package oogasalad.engine.model.conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public abstract class Condition {

  protected int[] myParameters;

  protected Condition(int[] parameters) {
    myParameters = parameters;
  }

  /**
   * Checks if this condition evaluates to true given a board and point
   * @param board
   * @param referencePoint reference point for this condition
   * @return
   */
  public abstract boolean isTrue(Board board, Position referencePoint);
}
