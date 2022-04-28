package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that tests the IsPlayer  Condition
 * @author Haris Adnan
 */
class IsPlayerTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);
  /**
   * Function that checks if the isTrue function works for the IsPlayer Condition.
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,0};
    IsPlayer isPlayer = new IsPlayer(paramarray);
    boolean answer = isPlayer.isTrue(TestBoard, new Position(1,1));
    assertEquals(TestBoard.getPlayer(), 0);
  }
}