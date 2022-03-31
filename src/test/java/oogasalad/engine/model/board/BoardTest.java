package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.prism.RectShadowGraphics;
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
  void placeNewPiece() {
    TestBoard.placeNewPiece(1,1, new Piece("...", 1));
    assertNotNull(TestBoard.getMyBoard()[1][1]);
  }

  @Test
  void remove() {
    TestBoard.placeNewPiece(1,1, new Piece("...", 1));
    assertNotNull(TestBoard.getMyBoard()[1][1]);
    TestBoard.remove(1,1);
    assertNull(TestBoard.getMyBoard()[1][1]);
  }

  @Test
  void getPiece() {
    TestBoard.placeNewPiece(1,1, new Piece("...", 1));
    Piece testPiece = TestBoard.getPiece(1,1);
    assertNotNull(testPiece);
  }

  @Test
  void move() {
    Piece TestPiece = new Piece("...", 1);
    TestBoard.placeNewPiece(1,1, TestPiece);
    TestBoard.move(2,2, TestPiece);=
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