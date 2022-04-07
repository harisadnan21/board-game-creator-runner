package oogasalad.engine.model.conditions.board_conditions;
import oogasalad.engine.model.board.Board;

/**
 * Interface for a condition that has to check the state of the whole board or multiple pieces on it.
 * @author Robert Cranston
 */
public interface BoardCondition {
  /**
   * evaluates if the condition is true
   * @param board current board state
   */
  boolean isTrue(Board board);
}
