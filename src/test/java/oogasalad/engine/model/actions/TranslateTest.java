package oogasalad.engine.model.actions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TranslateTest {

  Board myBoard;
  Action myTranslate;

  @BeforeEach
  void setup() {
    myBoard = new Board(5, 5);

    myTranslate = new Translate(new int[]{0,0,2,2});
  }

  @Test
  void basicTest() throws OutOfBoardException {

    myBoard = myBoard.placeNewPiece(0, 0, 0, 0);

    assertFalse(myBoard.isEmpty(0,0));
    assertTrue(myBoard.isEmpty(2,2));

    myBoard = myTranslate.execute(myBoard, 0,0);

    assertTrue(myBoard.isEmpty(0,0));
    assertFalse(myBoard.isEmpty(2,2));

  }

  @Test
  void relativeTest() {
    myBoard = myBoard.placeNewPiece(1,1,0,0);

    assertFalse(myBoard.isEmpty(1,1));
    assertTrue(myBoard.isEmpty(3,3));

    myBoard = myTranslate.execute(myBoard, 1,1);

    assertTrue(myBoard.isEmpty(1,1));
    assertFalse(myBoard.isEmpty(3,3));
  }
}
