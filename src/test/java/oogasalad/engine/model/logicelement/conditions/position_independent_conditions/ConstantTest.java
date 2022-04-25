package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the Constant Board Condition
 * @author Haris Adnan, Ricky Weerts
 */
class ConstantTest {
  Board TestBoard = new Board(4, 4);
  /**
   * Function that checks if the isTrue function works for the False Condition.
   */
  @Test
  void isTrue() {
    int[] params = new int[]{0};
    Constant falseCond = new Constant(params);
    assertFalse(falseCond.isTrue(TestBoard, new Position(2,2)));

    params = new int[]{1};
    Constant trueCond = new Constant(params);
    assertTrue(trueCond.isTrue(TestBoard, new Position(2,2)));
  }
}