package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  Board myBoard = new ArrayBoard(3, 3);

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
    assertTrue(!myBoard.getPositionStateAt(1,1).isEmpty());
  }

  @Test
  void remove() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    assertTrue(!myBoard.getPositionStateAt(1,1).isEmpty());
    myBoard.remove(1,1);
    assertTrue(myBoard.getPositionStateAt(1,1).isEmpty());
  }

  @Test
  void getPieceRecord() throws OutOfBoardException {
    myBoard.placeNewPiece(1,1, 0, 0);
    Piece testPiece = myBoard.getPiece(1,1);
    assertTrue(!testPiece.equals(Piece.EMPTY));
  }

  @Test
  void move() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(0,0,0,0);
    myBoard = myBoard.move(0, 0, 2,2);
    assertFalse(myBoard.getPiece(2,2).equals(Piece.EMPTY));
  }

  @Test
  void deepCopyTest() throws OutOfBoardException {
    myBoard.placeNewPiece(1, 1, 0, 0);

    Board copyBoard = myBoard.copy();
    assertTrue(copyBoard.getPositionStateAt(1, 1).isPresent());
    assertFalse(copyBoard.getPositionStateAt(0, 1).isPresent());
  }

  @Test
  void copyConstructorTest() throws OutOfBoardException {
    myBoard.placeNewPiece(1, 1, 0, 0);

    ArrayBoard board = new ArrayBoard((ArrayBoard) myBoard);
    assertTrue(!board.getPositionStateAt(1, 1).isEmpty());
    assertFalse(board.getPositionStateAt(0, 1).isEmpty());
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