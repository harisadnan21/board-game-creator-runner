package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.logicelement.actions.Place;
import org.junit.jupiter.api.Test;

/**
 * Class that checks for the class IsAtAbsolute
 * @author Haris Adnan
 */
class IsAtAbsoluteTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);

  /**
   * test for the isTrue Function in the IsAbsoluteClass
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,3,3,0};
    IsAtAbsolute isAtAbsolute= new IsAtAbsolute(paramarray);
    Position position = new Position(2,2);
    boolean answer = isAtAbsolute.isTrue(TestBoard, position);
    assertTrue(answer);
  }
}