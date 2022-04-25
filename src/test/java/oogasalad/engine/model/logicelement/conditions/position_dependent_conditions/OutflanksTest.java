package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class Outflanks condition
 *
 */
class OutflanksTest {
  private Board TestBoard;

  @BeforeEach
  void setup() {
    TestBoard = new Board(4, 4)
            .placeNewPiece(0, 0, 1, 0)
            .placeNewPiece(0, 1, 1, 1)
            .placeNewPiece(0, 2, 1, 1)
            .placeNewPiece(0, 3, 1, 0)
            // diagonal
            .placeNewPiece(1, 1, 1, 1)
            .placeNewPiece(2, 2, 1, 1)
            .placeNewPiece(3, 3, 1, 0);
  }

  /**
   * test for the isTrue Function in the Outflanks Class
   */
  @Test
  void isTrue() {
    int[] params = new int[]{0, 0, 0, 1, 1};
    Outflanks outflanks = new Outflanks(params);
    Position position = new Position(2,1);
    assertTrue(outflanks.isTrue(TestBoard, position));

    params[0] = 1;
    outflanks = new Outflanks(params);
    assertFalse(outflanks.isTrue(TestBoard, position));

    params = new int[]{0, 0, 1, 1, 1};
    outflanks = new Outflanks(params);
    assertTrue(outflanks.isTrue(TestBoard, position));

    params = new int[]{0, 0, 0, 1, 0};
    outflanks = new Outflanks(params);
    assertFalse(outflanks.isTrue(TestBoard, position));
  }
}