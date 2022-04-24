package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.LogicElement;

/**
 * Every action subclass constructor receives all the parameters
 * it needs to run from a relative position
 *
 * @author Jake Heller
 */
public abstract class Action extends LogicElement {

  protected Action(int[] parameters) {
    super(parameters);
  }

  /**
   *
   * @param board
   * @param referencePoint
   * @return
   */
  public abstract Board execute(Board board, Position referencePoint);
}
