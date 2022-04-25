package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class IsPieceTypeRay condition
 * @author Haris Adnan
 */
class IsPieceTypeRayTest {
  PositionState[][] positionStates = new PositionState[10][10];
  Board TestBoard = new Board(positionStates);
  /**
   * test for the IsTrue Function in the IsPieceTypeRay Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{1,1,1,1,5,1,1};
    IsPieceTypeRay isPieceTypeRay= new IsPieceTypeRay(paramarray);
    Position position = new Position(2,2);
    boolean answer = isPieceTypeRay.isTrue(TestBoard, position);
    assertFalse(answer);

  }
}