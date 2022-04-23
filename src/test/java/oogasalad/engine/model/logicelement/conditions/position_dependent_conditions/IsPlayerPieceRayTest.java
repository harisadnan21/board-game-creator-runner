package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsPlayerPieceRay condition
 * @author Haris Adnan
 */

class IsPlayerPieceRayTest {
  Board TestBoard = new Board(10,10);
  /**
   * test for the IsTrue Function in the IsPlayerPieceRay Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,1,1,5,1,1};
    IsPlayerPieceRay isPlayerPieceRay= new IsPlayerPieceRay(paramarray);
    Position position = new Position(1,1);
    boolean answer = isPlayerPieceRay.isTrue(TestBoard, position);
    assertFalse(answer);
  }
}