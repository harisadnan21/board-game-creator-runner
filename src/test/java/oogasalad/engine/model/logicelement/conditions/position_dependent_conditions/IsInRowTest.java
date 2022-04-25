package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsInRow condition
 * @author Haris Adnan
 */
class IsInRowTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);

  /**
   * test for the IsTrue Function in the IsInColumn Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{0, 1, 0};
    IsInRow isInRow= new IsInRow(paramarray);
    Position position = new Position(1,1);
    boolean answer = isInRow.isTrue(TestBoard, position);
    assertTrue(answer);
  }
}