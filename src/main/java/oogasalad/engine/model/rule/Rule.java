package oogasalad.engine.model.rule;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public interface Rule {

  /**
   * Checks if rule is valid and can be applied
   * @param board
   * @param referencePoint
   * @return
   */
  boolean isValid(Board board, Position referencePoint);

  /**
   * Returns the name given to this rule
   * @return
   */
  String getName();
}
