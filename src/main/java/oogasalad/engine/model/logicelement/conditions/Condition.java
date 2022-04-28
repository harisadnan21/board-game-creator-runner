package oogasalad.engine.model.logicelement.conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.LogicElement;

/**
 * Defines a condition, which takes in a list of ints
 * and evaluates to true or false given a board and position
 * @author Jake Heller
 */
public abstract class Condition extends LogicElement {

  protected Condition(int[] parameters) {
    super(parameters);
  }

  /**
   * Checks if this condition evaluates to true given a board and point
   * @param board
   * @param referencePoint reference point for this condition
   * @return
   */
  public abstract boolean isTrue(Board board, Position referencePoint);

  /**
   * Flips result if invert is true
   * @param result
   * @param invert
   * @return
   */
  protected boolean invertIfTrue(boolean result, boolean invert) {
    return invert != result;
  }
}
