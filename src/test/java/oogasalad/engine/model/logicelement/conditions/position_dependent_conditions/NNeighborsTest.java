package oogasalad.engine.model.logicelement.conditions.position_dependent_conditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import oogasalad.engine.model.logicelement.conditions.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jake Heller
 */
public class NNeighborsTest {

  Board myBoard;

  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
  }

  @Test
  void basicTest() throws OutOfBoardException {

    // [row, column, n, id, isAbsolute, invert]
    int[] params = new int[]{0, 0, 2, 0, 0, 0};
    Condition condition = new HasNNeighborsOfType(params);

    myBoard = myBoard.placeNewPiece(0,0,0,0);
    myBoard = myBoard.placeNewPiece(0,1,0,0);

    assertTrue(condition.isTrue(myBoard, new Position(1,0)));
    assertFalse(condition.isTrue(myBoard, new Position(0,2)));
  }
}
