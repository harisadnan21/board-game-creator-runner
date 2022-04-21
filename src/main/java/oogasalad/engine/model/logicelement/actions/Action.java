package oogasalad.engine.model.logicelement.actions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

/**
 * Every action subclass constructor receives all the parameters
 * it needs to run from a relative position
 *
 * @author Jake Heller
 */
public abstract class Action {

  protected int[] myParameters;
  protected Action(int[] parameters) {
    myParameters = parameters;
  }

  /**
   *
   * @param board
   * @param referencePoint
   * @return
   */
  public abstract Board execute(Board board, Position referencePoint);
}
