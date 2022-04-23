package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import oogasalad.engine.model.logicelement.conditions.Condition;
import oogasalad.engine.model.logicelement.conditions.position_dependent_conditions.IsEmpty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests HasPiece condition
 * @author Jake Heller
 */
public class HasPieceTest {

  Board myBoard;

  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
  }

  @Test
  void basicTest() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1, 1, 0, 0);
    int[] params = new int[]{0};
    Condition condition = new HasPiece(params);

    assertTrue(condition.isTrue(myBoard, new Position(0,1)));

    Condition has1 = new HasPiece(new int[]{1});
    assertFalse(has1.isTrue(myBoard, null));
  }
}
