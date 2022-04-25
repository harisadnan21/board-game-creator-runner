package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.logicelement.actions.Remove;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsEmpty condition
 * @author Haris Adnan
 */
class IsEmptyTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);

  /**
   * test for the IsTrue Function in the IsEmpty Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,1};
    IsEmpty isEmpty= new IsEmpty(paramarray);
    Position position = new Position(2,2);
    boolean answer = isEmpty.isTrue(TestBoard, position);
    assertTrue(answer);
  }
}