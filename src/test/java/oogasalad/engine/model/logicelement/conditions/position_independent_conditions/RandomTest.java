package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;
import org.junit.jupiter.api.Test;

/**
 * Tests RandomChance
 *
 * @author Jake Heller
 */
public class RandomTest {

  Board testBoard = new Board(4,4);

  /**
   * Function that checks if the isTrue function works for the IsPlayer Condition.
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{5,0,0};
    Condition cond = new RandomChance(paramarray);
    int count = 0;
    for (int i = 0; i < 100; i++) {
      boolean answer = cond.isTrue(null, null);
      count += answer ? 1 : 0;
    }

    // cannot directly check answer because it is random
    assertDoesNotThrow(() -> cond.isTrue(null, null));

    // this test is commented out because it is randomized but has very high
    // probability of being true
    // assertTrue(count > 5 && count < 35);
    Condition cond1 = new RandomChance(new int[]{5,1,0});

    // if param 1 is true, answer should be constant
    assertEquals(cond1.isTrue(null, null), cond1.isTrue(null, null));
  }
}
