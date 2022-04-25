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
  Board TestBoard = new Board(4, 4).placeNewPiece(2,3,4,0);
  /**
   * test for the IsTrue Function in the IsPieceType Class
   */
  @Test
  void isTrue() {
    int[] paramarray = new int[]{0,0,4,0,0};
    IsPieceType isPieceType = new IsPieceType(paramarray);
    assertTrue(isPieceType.isTrue(TestBoard, new Position(2,3)));
    assertFalse(isPieceType.isTrue(TestBoard, new Position(3,3)));
  }
}