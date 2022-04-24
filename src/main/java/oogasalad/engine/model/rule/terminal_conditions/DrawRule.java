package oogasalad.engine.model.rule.terminal_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;
import oogasalad.engine.model.logicelement.conditions.position_independent_conditions.False;
import oogasalad.engine.model.logicelement.winner.WinDecision;

/**
 * Draws are checked by the oracle, so when checking if it is valid should always return false
 */
public class DrawRule extends EndRule {

  /**
   * Sets end and winner conditions
   *
   * @param name
   * @param winDecision   Winner decision on how to determine a winner
   */
  public DrawRule(String name,
      WinDecision winDecision) {
    super(name, new Condition[]{}, winDecision);
  }

  @Override
  public boolean isValid(Board board, Position referencePoint) {
    return false;
  }
}
