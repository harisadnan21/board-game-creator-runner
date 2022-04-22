package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {
  Board myBoard = new Board(3, 3);

  @BeforeEach
  void setup() {
    myBoard = new Board(3, 3);
  }

  @Test
  void testPlacement() {
    Board b = myBoard.placePiece(new PositionState(new Position(0,1),new Piece(1,1)));
    assertTrue(b.isOccupied(0,1));
    assertFalse(myBoard.isOccupied(0,1));
  }

  @ParameterizedTest
  @MethodSource("testErrorsSource")
  void testValidity(Tuple3<Board, Integer, Integer> vals) {
    Board board = vals.v1;
    int minRow = 0;
    int minCol = 0;
    int maxRow = vals.v2 - 1;
    int maxCol = vals.v3 - 1;

    var invalidColVals = Seq.rangeClosed(-3,-1).append(Seq.rangeClosed(maxCol+1, maxCol+3));
    var invalidRowVals = Seq.rangeClosed(-3,-1).append(Seq.rangeClosed(maxRow+1, maxRow+3));
    var invalidPositions = invalidRowVals.crossJoin(invalidColVals).map(Position::new).toList();
    for(Position position: invalidPositions) {
      assertFalse(board.isValidRow(position.i()));
      assertFalse(board.isValidColumn(position.j()));
      assertFalse(board.isValidPositionCoordinates(position.i(), position.j()));
      assertFalse(board.isValidPosition(position));
    }
  }

  @ParameterizedTest
  @MethodSource("testErrorsSource")
  void testErrors(Tuple3<Board, Integer, Integer> vals) {
    Board board = vals.v1;
    int minRow = 0;
    int minCol = 0;
    int maxRow = vals.v2 - 1;
    int maxCol = vals.v3 - 1;
    var invalidRowVals = Seq.rangeClosed(-3,-1).append(Seq.rangeClosed(maxRow+1, maxRow+3));
    var invalidColVals = Seq.rangeClosed(-3,-1).append(Seq.rangeClosed(maxCol+1, maxCol+3));
    var invalidPositions = invalidRowVals.crossJoin(invalidColVals).map(Position::new).toList();
    for(Position position: invalidPositions) {
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.getPositionStateAt(position.i(), position.j()));
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.getPositionStateAt(position));
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.getPiece(position.i(), position.j()));
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.removePiece(position));
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.placePiece(new PositionState(position, new Piece(1,1))));
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.movePiece(position, new Position(0,0)));
      Assertions.assertThrowsExactly(OutOfBoardException.class, () -> board.isOccupied(position.i(), position.j()));
      assertThrowsExactly(OutOfBoardException.class, () -> board.getPositionStateAt(position.i(), position.j()));
      assertThrowsExactly(OutOfBoardException.class, () -> board.getPositionStateAt(position));
      assertThrowsExactly(OutOfBoardException.class, () -> board.getPiece(position.i(), position.j()));
      assertThrowsExactly(OutOfBoardException.class, () -> board.removePiece(position));
      assertThrowsExactly(OutOfBoardException.class, () -> board.placePiece(new PositionState(position, new Piece(1,1))));
      assertThrowsExactly(OutOfBoardException.class, () -> board.movePiece(position, new Position(0,0)));
      assertThrowsExactly(OutOfBoardException.class, () -> board.isOccupied(position.i(), position.j()));
    }
  }

  static Stream<Tuple3<Board, Integer, Integer>> testErrorsSource() {
    Integer squareBoardLength = 2;
    Integer recBoardNumRows1 = 5;
    Integer recBoardNumCols1 = 2;
    Integer recBoardNumRows2 = 3;
    Integer recBoardNumCols2 = 7;

    Board b1 = new Board(squareBoardLength, squareBoardLength);
    Board b2 = new Board(recBoardNumRows1, recBoardNumCols1);
    Board b3 = new Board(recBoardNumRows2, recBoardNumCols2);
    return Stream.of(new Tuple3(b1, squareBoardLength, squareBoardLength), new Tuple3(b2, recBoardNumRows1, recBoardNumCols1), new Tuple3(b3, recBoardNumRows2, recBoardNumCols2));
  }

  @Test
  void activePlayer() {
    Board board = new Board(3,3);
    Random random = new Random(10);
    int iterations = 10;
    int[] players = {Piece.PLAYER_ONE, Piece.PLAYER_TWO};
    int nextPlayer = Piece.PLAYER_ONE;
    for(int i = 0; i < iterations; i++) {
      if(random.nextDouble() < .5) {
        nextPlayer = Piece.PLAYER_ONE;
      } else {
        nextPlayer = Piece.PLAYER_TWO;
      }
      board = board.setPlayer(nextPlayer);
      assertEquals(board.getPlayer(), nextPlayer);
    }
  }

  @Test
  void goodErrors() {
    Random random = new Random(5);
    List<Board> boards = new LinkedList<>();
    Seq.rangeClosed(1, 5).forEach(num -> boards.add(new Board(num, num)));
    int[] nums = random.ints(5, -100, -1).toArray();
    for(Board board: boards) {
      assertThrowsExactly(OutOfBoardException.class, () -> board.move(nums[0], nums[1], nums[2], nums[3]));
      assertThrowsExactly(OutOfBoardException.class, () -> board.remove(nums[0], nums[1]));
      assertThrowsExactly(OutOfBoardException.class, () -> board.placeNewPiece(nums[0], nums[1], nums[2], nums[3]));
    }
  }

  @Test
  void testBoardIsPersistentBasic() {
    Board b1 = new Board(4,4);
    Board b2 = b1.placePiece(new PositionState(new Position(1,1), new Piece(1,1)));
    assertNotEquals(b1, b2);
    assertNotEquals(b1.getPositionStateAt(1,1), b2.getPositionStateAt(1,1));
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
    Board b6 = b5.removePiece(new Position(1, i));
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
      assertNotEquals(board1, board2);
      assertNotSame(board1, board2);
    }
  }


}

  @Test
  void placeNewPiece() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1,1, 0, 0);
    assertTrue(myBoard.isOccupied(1,1));
  }

  @Test
  void remove() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1,1, 0, 0);
    assertTrue(myBoard.isOccupied(1,1));
    myBoard = myBoard.remove(1,1);
    assertTrue(myBoard.isEmpty(1,1));
  }

  @Test
  void removeRobust() {
    PositionState[][] positionStates = new PositionState[3][3];
    Seq.range(0, 3).crossSelfJoin().map(Position::new).forEach(position -> positionStates[position.i()][position.j()] = new PositionState(position, new Piece(1, Piece.PLAYER_ONE)));
    Board board = new Board(positionStates);
    Board emptyBoard = Seq.range(0, 3).crossSelfJoin().map(Position::new).foldLeft(board, (currBoard, position) -> currBoard.removePiece(position));

    // Validate that emptyBoard is indeed empty
    assertTrue(
        BoardUtilities.getNotSatisfyingPositionStatesSeq(emptyBoard, positionState -> positionState.piece().equals(Piece.EMPTY)).isEmpty());
    assertEquals(BoardUtilities.getSatisfyingPositionStatesSeq(
        emptyBoard, positionState -> positionState.piece().equals(Piece.EMPTY)).count(), (3 * 3));
    //TODO: implement Board.equals() so that this will work:
    //Assertions.assertEquals(emptyBoard, new Board(3,3));

    // Validate that board is still full
    board.getPositionStatesStream().forEach(positionState -> assertNotEquals(positionState.piece(), Piece.EMPTY));
    board.getPositionStatesStream().forEach(positionState -> assertFalse(board.isEmpty(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertTrue(board.isOccupied(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertNotEquals(positionState.piece(), (Piece.EMPTY)));
    board.getPositionStatesStream().forEach(positionState -> assertFalse(board.isEmpty(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertTrue(board.isOccupied(positionState.i(), positionState.j())));

  }

  @Test
  void emptyBoardInit() {
    Board board = new Board(3,3);
    Stream<PositionState> positionStateStream = board.getPositionStatesStream();
    positionStateStream.forEach(positionState -> assertEquals(positionState.piece(), Piece.EMPTY));
  }

  @Test
  void fullBoardInit() {
    PositionState[][] positionStates = new PositionState[3][3];
    Seq.range(0, 3).crossSelfJoin().map(Position::new).forEach(position -> positionStates[position.i()][position.j()] = new PositionState(position, new Piece(1, Piece.PLAYER_ONE)));
    Board board = new Board(positionStates);
    board.getPositionStatesStream().forEach(positionState -> assertNotEquals(positionState.piece(), Piece.EMPTY));
    board.getPositionStatesStream().forEach(positionState -> assertNotEquals(positionState.player(), Piece.NO_PLAYER));
    board.getPositionStatesStream().forEach(positionState -> assertNotEquals(positionState.type(), Piece.BLANK_TYPE));
    board.getPositionStatesStream().forEach(positionState -> assertEquals(positionState.player(), Piece.PLAYER_ONE));
    board.getPositionStatesStream().forEach(positionState -> assertFalse(board.isEmpty(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertTrue(board.isOccupied(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertFalse(board.isEmpty(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertTrue(board.isOccupied(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertTrue(board.isValidPositionCoordinates(positionState.i(), positionState.j())));
    board.getPositionStatesStream().forEach(positionState -> assertEquals(positionState.piece(), new Piece(1, Piece.PLAYER_ONE)));
  }

  @Test
  void getPositionState() throws OutOfBoardException {
    myBoard = myBoard.placeNewPiece(1,1, 0, 0);
    PositionState testPos = myBoard.getPositionStateAt(1,1);
    assertNotNull(testPos);
  }

  @Test
  void move() throws OutOfBoardException {
    myBoard = myBoard.move(0, 0, 2,2);
    assertNotNull(myBoard.getPositionStateAt(2,2));

  }


  @Test
  void boardEquality() {
    Board b1 = new Board(1,1);
    Board b2 = new Board(1,1);
    Board b3 = new Board(5,5);
    Board b4 = new Board(5,5);

    Board b5 = new Board(5,5);
    b5 = b5.placePiece(new PositionState(new Position(0,0), new Piece(1,1)));

    Board b6 = new Board(5,5);
    b6 = b6.placePiece(new PositionState(new Position(0,0), new Piece(1,1)));

    assertEquals(b1, b2);
    assertEquals(b3, b4);
    assertEquals(b5, b6);

    assertNotEquals(b1, b3);
    assertNotEquals(b1, b4);
    assertNotEquals(b1, b5);
    assertNotEquals(b1, b6);


    assertNotEquals(b3, b5);
    assertNotEquals(b3, b5);

  }

  @Test
  void testToString() {
    Board b1 = new Board(4,4);
    Board b2 = new Board(2,2);
    assertInstanceOf(String.class, b1.toString());
    assertNotEquals("", b1.toString());
    assertNotEquals(b1.toString(), b2.toString());

  }

  @Test
  void piecesByPlayerEmptyBoard() {
  }

  private Seq<Map<Integer, Integer>> getMap(Board[] boards) {
    return Seq.seq(Arrays.stream(boards)).map(BoardUtilities::numPiecesByPlayer);
  }

  @Test
  void numPiecesByPlayer() {
    Board[] boards = { new Board(1,1), new Board(3,3), new Board(9,9 )};
    Seq<Map<Integer, Integer>> maps = getMap(boards);
    maps.forEach(integerListMap -> assertEquals(0, integerListMap.get(Piece.PLAYER_ONE) ));
    maps = getMap(boards);
    maps.forEach(integerListMap -> assertEquals(0, integerListMap.get(Piece.PLAYER_TWO) ));
    maps = getMap(boards);
    Seq.zip(maps, Stream.of(boards)).forEach(tuple2 -> assertEquals(tuple2.v1.get(Piece.NO_PLAYER), tuple2.v2.getHeight() * tuple2.v2.getWidth()));
  }
}