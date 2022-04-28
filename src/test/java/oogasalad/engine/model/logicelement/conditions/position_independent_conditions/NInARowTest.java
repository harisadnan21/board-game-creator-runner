package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class that tests the N In A Row Board Condition
 * @author Ricky Weerts
 */
class NInARowTest {
  Board TestBoard;

  @BeforeEach
  void setup() {
    TestBoard = new Board(4, 4);
  }

  @Test
  void testDiagonal() {
    NInARow nInARow = new NInARow(new int[]{3, 1, 0});
    Position pos = new Position(2,2);
    assertFalse(nInARow.isTrue(TestBoard, pos));
    TestBoard = TestBoard.placeNewPiece(1, 1, 1, 1).placeNewPiece(2, 2, 1, 1);
    assertTrue(new NInARow(new int[]{2, 1, 0}).isTrue(TestBoard, pos));
    assertFalse(nInARow.isTrue(TestBoard, pos));
    TestBoard = TestBoard.placeNewPiece(3, 3, 1, 1);
    assertTrue(nInARow.isTrue(TestBoard, pos));
  }

  @Test
  void testHorizontal() {
    NInARow nInARow = new NInARow(new int[]{3, 1, 0});
    Position pos = new Position(2,2);
    TestBoard = TestBoard.placeNewPiece(1, 1, 1, 1).placeNewPiece(1, 2, 1, 1);
    assertTrue(new NInARow(new int[]{2, 1, 0}).isTrue(TestBoard, pos));
    assertFalse(nInARow.isTrue(TestBoard, pos));
    TestBoard = TestBoard.placeNewPiece(1, 3, 1, 1);
    assertTrue(nInARow.isTrue(TestBoard, pos));
  }

  @Test
  void testVertical() {
    NInARow nInARow = new NInARow(new int[]{3, 1, 0});
    Position pos = new Position(2,2);
    TestBoard = TestBoard.placeNewPiece(1, 1, 1, 1).placeNewPiece(0, 1, 1, 1);
    assertTrue(new NInARow(new int[]{2, 1, 0}).isTrue(TestBoard, pos));
    assertFalse(nInARow.isTrue(TestBoard, pos));
    TestBoard = TestBoard.placeNewPiece(2, 1, 1, 1);
    assertTrue(nInARow.isTrue(TestBoard, pos));
  }
}