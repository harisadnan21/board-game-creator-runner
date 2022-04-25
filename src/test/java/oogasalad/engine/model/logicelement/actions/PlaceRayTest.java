package oogasalad.engine.model.logicelement.actions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaceRayTest {

  Board myBoard = new Board(4,4);
  Action myPlaceRay;

  @BeforeEach
  void setup() {
    myBoard = new Board(5, 5);

    myPlaceRay = new PlaceRay(new int[]{0,0,1,0,6,0,0,0});
  }

  @Test
  void basicTest() throws OutOfBoardException {

    myBoard = myBoard.placeNewPiece(0, 3, 0, 0);

    myBoard = myPlaceRay.execute(myBoard, new Position(0,0));

    for (int i = 0; i < 5; i++) {
      assertTrue(myBoard.isOccupied(i,0));
    }
    assertTrue(myBoard.isOccupied(0,0));
    assertTrue(myBoard.isOccupied(1,0));

  }
}
