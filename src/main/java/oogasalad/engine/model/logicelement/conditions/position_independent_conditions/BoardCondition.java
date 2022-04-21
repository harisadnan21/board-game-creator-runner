package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;

/**
 * Super Class for a condition that has to check the state of the whole board or multiple pieces on it.
 * @author Robert Cranston
 */
public abstract class BoardCondition extends Condition {

  protected BoardCondition(int[] parameters){
    super(parameters);
  }

  /**
   * evaluates if the condition is true
   * @param board current board state
   * @param referencePoint
   */
  public abstract boolean isTrue(Board board, Position referencePoint);
}
