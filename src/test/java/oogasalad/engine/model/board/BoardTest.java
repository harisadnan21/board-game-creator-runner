package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  Board myBoard = new Board(3, 3);

  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
  }

  @Test
  void testPlacement() throws CloneNotSupportedException {
    Board b = myBoard.placePiece(new PositionState(new Position(0,1),new Piece(1,1)));
    assertTrue(b.hasPieceAtLocation(0,1));
    assertFalse(myBoard.hasPieceAtLocation(0,1));
  }

  @Test
  void testBoardIsPersistentBasic() throws CloneNotSupportedException {
    Board b1 = new Board(4,4);
    Board b2 = b1.placePiece(new PositionState(new Position(1,1), new Piece(1,1)));
    Assertions.assertNotEquals(b1, b2);
    Assertions.assertNotEquals(b1.getPositionStateAt(1,1), b2.getPositionStateAt(1,1));
  }

@Test
void testBoardIsPersistentAdvanced() throws CloneNotSupportedException {
  Board b1 = new Board(8,8);
  List<Board> boards = new ArrayList<>();
  boards.add(b1);
  for(int i = 1; i < 3; i++){
    Board b2 = b1.placePiece(new PositionState(new Position(1, i), new Piece(1, Piece.PLAYER_ONE)));
    Board b3 = b2.placePiece(new PositionState(new Position(2, i), new Piece(1, Piece.PLAYER_TWO)));
    Board b4 = b3.movePiece(new Position(2,i), new Position(3, i));
    Board b5 = b4.placePiece(new PositionState(new Position(0,i), new Piece(1, Piece.PLAYER_ONE)));
    Board b6 = b5.removePiece(new Position(2, i));
    boards.add(b2);
    boards.add(b3);
    boards.add(b4);
    boards.add(b5);
    boards.add(b6);
  }

  for(int i = 0; i < boards.size(); i++) {
    Board board1 = boards.get(i);
    for(int j = i+1; j < boards.size(); j++) {
      Board board2 = boards.get(j);
      Assertions.assertNotEquals(board1, board2);
    }
  }


}
//  @Test
//  void getMyBoard() {
//    //assertEquals(TestBoard, TestBoard.getMyBoard());
//  }
//
//  @Test
//  void selectCell() {
//  }
//
//  @Test
//  void placeNewPiece() throws OutOfBoardException {
//    myBoard.placeNewPiece(1,1, 0, 0);
//    assertTrue(myBoard.getPieceRecord(1,1).isPresent());
//  }
//
//  @Test
//  void remove() throws OutOfBoardException {
//    myBoard.placeNewPiece(1,1, 0, 0);
//    assertTrue(myBoard.getPieceRecord(1,1).isPresent());
//    myBoard.remove(1,1);
//    assertTrue(myBoard.getPieceRecord(1,1).isEmpty());
//  }
//
//  @Test
//  void getPieceRecord() throws OutOfBoardException {
//    myBoard.placeNewPiece(1,1, 0, 0);
//    Optional<PieceRecord> testPiece = myBoard.getPieceRecord(1,1);
//    assertNotNull(testPiece);
//  }
//
//  @Test
//  void move() throws OutOfBoardException {
//    OldPiece TestPiece = new OldPiece(0, 1, 0, 0 );
//
//    myBoard.move(0, 0, 2,2);
//    assertNotNull(myBoard.getPieceRecord(2,2));
//
//  }
//
//  @Test
//  void deepCopyTest() throws OutOfBoardException {
//    myBoard.placeNewPiece(1, 1, 0, 0);
//    Board copyBoard = myBoard.deepCopy();
//    assertTrue(copyBoard.getPieceRecord(1, 1).isPresent());
//    assertFalse(copyBoard.getPieceRecord(0, 1).isPresent());
//  }
//
//  @Test
//  void copyConstructorTest() throws OutOfBoardException {
//    myBoard.placeNewPiece(1, 1, 0, 0);
//
//    Board board = new Board(myBoard);
//    assertTrue(board.getPieceRecord(1, 1).isPresent());
//    assertFalse(board.getPieceRecord(0, 1).isPresent());
//  }
//
//  @Test
//  void isValid() {
//  }
//
//  @Test
//  void deepCopy() {
//  }
//
//  @Test
//  void iterator() {
//
//  }
}