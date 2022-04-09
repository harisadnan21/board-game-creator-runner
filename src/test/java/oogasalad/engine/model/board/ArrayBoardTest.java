package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayBoardTest {
  ArrayBoard myBoard = new ArrayBoard(3, 3);

  @BeforeEach
  void setup() {
    myBoard = new ArrayBoard(3, 3);
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
    assertTrue(myBoard.getPiece(1,1).isPresent());
  }

  @Test
  void remove() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    assertTrue(myBoard.getPiece(1,1).isPresent());
    myBoard.remove(1,1);
    assertTrue(myBoard.getPiece(1,1).isEmpty());
  }

  @Test
  void getPieceRecord() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    Optional<Piece> testPiece = myBoard.getPiece(1,1);
    assertNotNull(testPiece.get());
  }

  @Test
  void move() throws OutOfBoardException {
    myBoard.move(0, 0, 2,2);
    assertNotNull(myBoard.getPiece(2,2).get());
  }

  @Test
  void deepCopyTest() throws OutOfBoardException {
    myBoard.placeNewPiece(1, 1, 0, 0);
    ArrayBoard copyBoard = myBoard.copy();
    assertTrue(copyBoard.getPiece(1, 1).isPresent());
    assertFalse(copyBoard.getPiece(0, 1).isPresent());
  }

  @Test
  void copyConstructorTest() throws OutOfBoardException {
    myBoard.placeNewPiece(1, 1, 0, 0);

    ArrayBoard board = new ArrayBoard(myBoard);
    assertTrue(board.getPiece(1, 1).isPresent());
    assertFalse(board.getPiece(0, 1).isPresent());
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