package oogasalad.engine.model.logicelement.actions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test used for RemoveRay bug fix
 * @author Jake Heller
 */
public class RemoveRayTest {
  Board myBoard;
  Action myRemoveRay;

  @BeforeEach
  void setup() {
    myBoard = new Board(5, 5);
  }

  @Test
  void basicTest() throws OutOfBoardException {

    int[] parameters = new int[6];

    parameters[3] = 1;
    parameters[4] = 2;

    assertDoesNotThrow(() -> myRemoveRay = new RemoveRay(parameters));

    myRemoveRay = new RemoveRay(parameters);

    myBoard = myBoard.placeNewPiece(0, 1, 0, 0);

    myBoard = myRemoveRay.execute(myBoard, new Position(0,0));

    assertTrue(myBoard.isEmpty(0,1));

  }
}
