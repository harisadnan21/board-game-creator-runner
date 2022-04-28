package oogasalad.engine.model.logicelement.actions;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Class that checks for the class Outflank action
 *
 */
class OutflankTest {
  private Board TestBoard;

  @BeforeEach
  void setup() {
    TestBoard = new Board(4, 4)
            .placeNewPiece(0, 0, 1, 0)
            .placeNewPiece(0, 1, 2, 1)
            .placeNewPiece(0, 2, 2, 1)
            .placeNewPiece(0, 3, 1, 0)
            // diagonal
            .placeNewPiece(1, 1, 2, 1)
            .placeNewPiece(2, 2, 2, 1)
            .placeNewPiece(3, 3, 1, 0);
  }

  /**
   * test for the execute Function in the Outflank Class
   */
  @Test
  void execute() {
    Board afterColumnFlank = new Board(4, 4)
            .placeNewPiece(0, 0, 1, 0)
            .placeNewPiece(0, 1, 1, 0)
            .placeNewPiece(0, 2, 1, 0)
            .placeNewPiece(0, 3, 1, 0)
            // diagonal
            .placeNewPiece(1, 1, 2, 1)
            .placeNewPiece(2, 2, 2, 1)
            .placeNewPiece(3, 3, 1, 0);
    Board afterDiagonalFlank = new Board(4, 4)
            .placeNewPiece(0, 0, 1, 0)
            .placeNewPiece(0, 1, 2, 1)
            .placeNewPiece(0, 2, 2, 1)
            .placeNewPiece(0, 3, 1, 0)
            // diagonal
            .placeNewPiece(1, 1, 1, 0)
            .placeNewPiece(2, 2, 1, 0)
            .placeNewPiece(3, 3, 1, 0);

    int[] params = new int[]{0, 0, 0, 1, 1};
    Outflank outflank = new Outflank(params);
    Position position = new Position(2,1);
    assertEquals(outflank.execute(TestBoard, position), afterColumnFlank);

    params[0] = 1;
    outflank = new Outflank(params);
    assertEquals(outflank.execute(TestBoard, position), TestBoard);

    params = new int[]{0, 0, 1, 1, 1};
    outflank = new Outflank(params);
    assertEquals(outflank.execute(TestBoard, position), afterDiagonalFlank);

    params = new int[]{0, 0, 0, 1, 0};
    outflank = new Outflank(params);
    assertEquals(outflank.execute(TestBoard, position), TestBoard);
  }
}