package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.prism.RectShadowGraphics;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.Piece;
import org.junit.jupiter.api.Test;

class BoardTest {
  Board TestBoard = new Board(3, 3);

  @Test
  void getMyBoard() {
    //assertEquals(TestBoard, TestBoard.getMyBoard());
  }

  @Test
  void selectCell() {
  }

  @Test
  void placeNewPiece() throws OutOfBoardException {
    TestBoard.placeNewPiece(1,1, new Piece("...", 1,0,0));
    assertNotNull(TestBoard.getMyBoard()[1][1]);
  }

  @Test
  void remove() throws OutOfBoardException {
    TestBoard.placeNewPiece(1,1, new Piece("...", 1,0,0));
    assertNotNull(TestBoard.getMyBoard()[1][1]);
    TestBoard.remove(1,1);
    assertNull(TestBoard.getMyBoard()[1][1]);
  }

  @Test
  void getPiece() throws OutOfBoardException {
    TestBoard.placeNewPiece(1,1, new Piece("...", 1,0,0));
    Piece testPiece = TestBoard.getPiece(1,1);
    assertNotNull(testPiece);
  }

  @Test
  void move() throws OutOfBoardException {
    Piece TestPiece = new Piece("...", 1, 0, 0 );

    TestBoard.move(2,2, TestPiece);
    assertNotNull(TestBoard.getPiece(2,2));

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