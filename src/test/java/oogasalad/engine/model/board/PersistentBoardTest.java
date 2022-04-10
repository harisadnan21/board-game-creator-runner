package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import oogasalad.engine.model.OutOfBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersistentBoardTest {
  Board myBoard = new MapBoard(3, 3);

  @BeforeEach
  void setup() {
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
    assertTrue(!myBoard.getPositionStateAt(1,1).isEmpty());
  }

  @Test
  void move() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(0,0,0,0);
    myBoard = myBoard.move(0, 0, 2,2);
    assertFalse(myBoard.getPositionStateAt(2,2).isEmpty());
  }

  @Test
  void deepCopyTest() throws OutOfBoardException {
    myBoard.placeNewPiece(1, 1, 0, 0);

    Board copyBoard = myBoard.copy();
    assertTrue(copyBoard.getPositionStateAt(1, 1).isPresent());
    assertFalse(copyBoard.getPositionStateAt(0, 1).isPresent());
  }

  @Test
  void isEmpty() {
    assertTrue(myBoard.isEmpty(2,2));
    assertTrue(myBoard.isEmpty(1,2));
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