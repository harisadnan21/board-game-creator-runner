package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsPieceType condition
 * @author Haris Adnan
 */
class IsPieceTypeTest {
  PositionState[][] positionStates = new PositionState[4][4];
  Board TestBoard = new Board(positionStates);
  /**
   * test for the IsTrue Function in the IsPieceType Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,1,1};
    IsOccupied isOccupied= new IsOccupied(paramarray);
    Position position = new Position(1,1);
    boolean answer = isOccupied.isTrue(TestBoard, position);
    assertFalse(answer);
  }
}