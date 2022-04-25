package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;

/**
 * Class that checks for the class IsOccupied condition
 * @author Haris Adnan
 */
class IsOccupiedTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);

  /**
   * test for the IsTrue Function in the IsOccupied Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,1,0};
    IsOccupied isOccupied= new IsOccupied(paramarray);
    Position position = new Position(2,2);
    boolean answer = isOccupied.isTrue(TestBoard, position);
    assertFalse(answer);
  }
}