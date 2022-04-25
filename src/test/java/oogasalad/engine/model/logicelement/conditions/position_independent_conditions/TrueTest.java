package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the True Board Condition
 * @author Haris Adnan
 */
class TrueTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);
  /**
   * Function that checks if the isTrue function works for the True Condition.
   */
  @Test
  void isTrue() {
    TestBoard.removePiece(new Position(1,1));
    int[] paramarray = new int[]{1,1,1};
    True trueCond = new True(paramarray);
    boolean answer = trueCond.isTrue(TestBoard, new Position(2,2));
    assertTrue(answer);
  }
}