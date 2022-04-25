package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsInColumn condition
 * @author Haris Adnan
 */
class IsInColumnTest {
  Board TestBoard = new Board(4,4);

  /**
   * test for the IsTrue Function in the IsInColumn Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{0,1};
    IsInColumn isInColumn= new IsInColumn(paramarray);
    Position position = new Position(1,1);
    boolean answer = isInColumn.isTrue(TestBoard, position);
    assertTrue(answer);
  }
}