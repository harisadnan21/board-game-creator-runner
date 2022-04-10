package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import oogasalad.engine.model.player.Player;
import org.jooq.SelectWhereStep;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Range;
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
  void testPlacement() {
    Board b = myBoard.placePiece(new PositionState(new Position(0,1),new Piece(1,1)));
    assertTrue(b.hasPieceAtLocation(0,1));
    assertFalse(myBoard.hasPieceAtLocation(0,1));
  }

  @Test
  void testBoardIsPersistentBasic() {
    Board b1 = new Board(4,4);
    Board b2 = b1.placePiece(new PositionState(new Position(1,1), new Piece(1,1)));
    Assertions.assertNotEquals(b1, b2);
    Assertions.assertNotEquals(b1.getPositionStateAt(1,1), b2.getPositionStateAt(1,1));
  }

@Test
void testBoardIsPersistentAdvanced() {
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

  @Test
  void placeNewPiece() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1,1, 0, 0);
    assertTrue(myBoard.hasPieceAtLocation(1,1));
  }

  @Test
  void remove() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1,1, 0, 0);
    assertTrue(myBoard.hasPieceAtLocation(1,1));
    myBoard = myBoard.remove(1,1);
    assertTrue(myBoard.isEmpty(1,1));
  }

  @Test
  void emptyBoardInit() {
    Board board = new Board(3,3);
    Stream<PositionState> positionStateStream = board.getPositionStatesStream();
    positionStateStream.forEach(positionState -> Assertions.assertTrue(positionState.piece().equals(Piece.EMPTY)));
  }

  @Test
  void fullBoardInit() {
    PositionState[][] positionStates = new PositionState[3][3];
    Seq.range(0, 3).crossSelfJoin().map(Position::new).forEach(position -> positionStates[position.i()][position.j()] = new PositionState(position, new Piece(1, Piece.PLAYER_ONE)));
    Board board = new Board(positionStates);
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertFalse(positionState.piece().equals(Piece.EMPTY)));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertFalse(positionState.player() == Piece.NO_PLAYER));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertFalse(positionState.type() == Piece.BLANK_TYPE));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertTrue(positionState.player() == Piece.PLAYER_ONE));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertFalse(board.isEmpty(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertTrue(board.hasPieceAtLocation(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertTrue(board.isValidPosition(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> Assertions.assertTrue(positionState.piece().equals(new Piece(1, Piece.PLAYER_ONE))));
  }

  @Test
  void getPositionState() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1,1, 0, 0);
    PositionState testPos = myBoard.getPositionStateAt(1,1);
    assertNotNull(testPos);
  }

  @Test
  void move() throws OutOfBoardException {
    OldPiece TestPiece = new OldPiece(0, 1, 0, 0 );

    myBoard = myBoard.move(0, 0, 2,2);
    assertNotNull(myBoard.getPositionStateAt(2,2));

  }

}