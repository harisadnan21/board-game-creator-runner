package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  Board myBoard = new Board(3, 3);

  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
  }
  @Test
  void getMyBoard() {
    //assertEquals(TestBoard, TestBoard.getMyBoard());
  }

  @Test
  void selectCell() {
  }

  @Test
  void placeNewPiece() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    assertNotNull(myBoard.getMyBoard()[1][1]);
  }

  @Test
  void remove() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    assertNotNull(myBoard.getMyBoard()[1][1]);
    myBoard.remove(1,1);
    assertNull(myBoard.getMyBoard()[1][1]);
  }

  @Test
  void getPiece() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    Piece testPiece = myBoard.getPiece(1,1).get();
    assertNotNull(testPiece);
  }

  @Test
  void move() throws OutOfBoardException {
    Piece TestPiece = new Piece(0, 1, 0, 0 );

    myBoard.move(0, 0, 2,2);
    assertNotNull(myBoard.getPiece(2,2));

  }

  @Test
  void deepCopyTest() throws OutOfBoardException {
    myBoard.placeNewPiece(1, 1, 0, 0);
    Board copyBoard = myBoard.deepCopy();
    assertTrue(copyBoard.getPiece(1, 1).isPresent());
    assertFalse(copyBoard.getPiece(0, 1).isPresent());
  }

  @Test
  void isValid() {
  }

  @Test
  void deepCopy() {
  }

  @Test
  void iterator() {

  }
}