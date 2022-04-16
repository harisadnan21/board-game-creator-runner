package oogasalad.engine.model.board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import io.vavr.collection.TreeMap;
import io.vavr.collection.SortedMap;
import oogasalad.engine.model.utilities.Utilities;
import org.jooq.lambda.Seq;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Class That defines the backend board and defines methods that can be applied to it.
 * @author Jake Heller, Haris Adnan, Robert Cranston, Alex Bildner
 */
@EqualsAndHashCode
@ToString
public class Board implements DisplayableBoard {

  public static final int NO_WINNER_YET = -2; //Eh
  public static final String INVALID_POSITION = "Invalid Position";
  private int activePlayer;
  private Set<Position> currentValidMoves; //Why does the Board care?
  private int myWinner = NO_WINNER_YET; //Why does the Board care?


  //TODO: update code to use these constants instead of magic numbers

  private final int numRows;
  private final int numCols;
  private static final int FIRST_ROW = 0;
  private static final int FIRST_COL = 0;
  private final int lastRow;
  private final int lastCol;

  private SortedMap<Position, PositionState> myBoard;

  public Board(PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numCols = Board.getNumColumnsInLongestRow(positionStates);
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    this.myBoard = getPositionStatesMap(positionStates);
  }

  public Board(int rows, int columns) {
    this.numRows = rows;
    this.numCols = columns;
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    PositionState[][] positionStates = getEmptyArrayOfPositionStates();
    this.myBoard = getPositionStatesMap(positionStates);
  }

  private TreeMap<Position, PositionState> getPositionStatesMap(
      PositionState[][] positionStates) {
    Seq<Position> positions = Seq.rangeClosed(FIRST_ROW, lastRow)
                              .crossJoin(Seq.rangeClosed(FIRST_COL, lastCol))
                              .map(Position::new);
    Map<Position, PositionState> map = positions.toMap(pos -> pos,
                                                       pos -> positionStates[pos.i()][pos.j()]);
    return TreeMap.ofAll(map);
  }

  private PositionState[][] getEmptyArrayOfPositionStates() {
    PositionState[][] positionStates = new PositionState[numRows][numCols];

    for (int i = FIRST_ROW; i <= lastRow; i++) {
      for (int j = FIRST_COL; j <= lastCol; j++) {
        positionStates[i][j] = new PositionState(new Position(i, j), Piece.EMPTY);
      }
    }
    return positionStates;
  }

  @Override
  public int getPlayer() {
    return activePlayer;
  }

  @Override
  public Board clone() {
    try {
      return (Board) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("bad");
    }

  }

  public Board copy() {
    return this.clone();
  }


  public Board removePiece(Position position) {
    throwIfInvalid(position);
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(position, new PositionState(position, Piece.EMPTY));
    return returnBoard;
  }

  private void throwIfInvalid(Position position) {
    if (!isValidPosition(position)) {
      throw new OutOfBoardException(INVALID_POSITION);
    }
  }

  public Board placePiece(PositionState positionState) {
    throwIfInvalid(positionState.position());
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(positionState.position(), positionState);
    return returnBoard;
  }

  public Board movePiece(Position oldPosition, Position newPosition) {
    throwIfInvalid(oldPosition);
    throwIfInvalid(newPosition);
    var oldPositionState = this.getPositionStateAt(oldPosition);
    Board returnBoard = this.clone();

    PositionState newPositionState = new PositionState(newPosition, oldPositionState.piece());

    returnBoard.myBoard = returnBoard.myBoard.put(oldPosition, new PositionState(oldPosition, Piece.EMPTY)).put(newPosition, newPositionState);
    return returnBoard;
  }

  @Override
  public PositionState getPositionStateAt(Position position) {
    return this.getPositionStateAt(position.i(), position.j());
  }


  private static int getNumColumnsInLongestRow(PositionState[][] positionStates) {
    return Arrays.stream(positionStates).mapToInt(Array::getLength).max().orElse(0);
  }

//  public boolean hasPieceAtLocation(int i, int j) {
//    PositionState positionState = myBoard.get(new Position(i, j)).get();
//    return positionState.piece() != Piece.EMPTY;
//  }


  @Override
  public boolean hasPieceAtLocation(int row, int column) {
    var positionState = myBoard.get(new Position(row, column)).getOrElseThrow(() -> new OutOfBoardException("Invalid"));
    return !positionState.piece().equals(Piece.EMPTY);
  }

  @Override
  public boolean isValidPosition(int x, int y) {
    return isValidI(x) && isValidJ(y);
  }

  public boolean isValidPosition(Position position) {
    return isValidI(position.i()) && isValidJ(position.j());
  }

  private boolean isValidJ(int j) {
    return (j >= FIRST_COL) && (j <= lastCol);
  }

  private boolean isValidI(int i) {
    return (i >= FIRST_ROW) && (i <= lastRow);
  }


  @Override
  public int getHeight() {
    return numRows;
  }

  @Override
  public int getWidth() {
    return numCols;
  }

  private boolean isValidY(int j) {
    return Utilities.isPositive(j) && (j < numCols);
  }

  private boolean isValidX(int i) {
    return Utilities.isPositive(i) && (i < numRows);
  }

  @Override
  public boolean isValidRow(int row) {
    return isValidI(row);
  }

  @Override
  public boolean isValidColumn(int column) {
    return isValidJ(column);
  }

  @Override
  public Stream<PositionState> getPositionStatesStream() {
    return myBoard.values().toJavaStream();
  }

  @Override
  public Seq<PositionState> getPositionStatesSeq() {
    return Seq.seq(myBoard.values().toJavaStream());
  }

  public Stream<PositionState> getSatisfyingPositionStatesStream(
      Predicate<PositionState> positionStatePredicate) {
    return getPositionStatesStream().filter(positionStatePredicate);
  }

  public Stream<PositionState> getNotSatisfyingPositionStatesStream(
      Predicate<PositionState> positionStatePredicate) {
    return myBoard.values().filterNot(positionStatePredicate).toJavaStream();
  }

  public Seq<PositionState> getNotSatisfyingPositionStatesSeq(
      Predicate<PositionState> positionStatePredicate) {
    return Seq.seq(this.getNotSatisfyingPositionStatesStream(positionStatePredicate));
  }

  public Seq<PositionState> getSatisfyingPositionStatesSeq(
      Predicate<PositionState> positionStatePredicate) {
    return Seq.seq(this.getSatisfyingPositionStatesStream(positionStatePredicate));
  }

  public Map<Integer, List<PositionState>> piecesByPlayer() {
    return getPositionStatesSeq().groupBy(PositionState::player);
  }

  public Map<Integer, Integer> numPiecesByPlayer(){
    Map<Integer, Integer> piecesByPlayer = Seq.seq(piecesByPlayer()).toMap(pair -> pair.v1, pair -> pair.v2.size());
    piecesByPlayer.putIfAbsent(Piece.PLAYER_ONE, 0);
    piecesByPlayer.putIfAbsent(Piece.PLAYER_TWO, 0);
    return piecesByPlayer;
  }

  @Override
  public PositionState getPositionStateAt(int i, int j) {
    return myBoard.get(new Position(i, j)).getOrElseThrow(() -> new OutOfBoardException(
        INVALID_POSITION));
  }

  @Override
  public Map<Integer, List<PositionState>> getRows() {
    return getPositionStatesSeq().groupBy(PositionState::i);
  }

  @Override
  public Map<Integer, List<PositionState>> getCols() {
    return getPositionStatesSeq().groupBy(PositionState::j);
  }

  @Override
  public Iterator<PositionState> iterator() {
    return getPositionStatesStream().iterator();
  }

  public boolean isEmpty(int i, int j) {
    return !hasPieceAtLocation(i,j);
  }

  @Deprecated
  public void setValidMoves(Set<Position> moves) {
    currentValidMoves = moves;
  }

  public Board setWinner(int winner) {
    Board copy = clone();
    copy.myWinner = winner;
    return copy;
  }

  /**
   * Places new piece with type and player at location (i,j)
   * @param i
   * @param j
   * @param type
   * @param player
   */
  public Board placeNewPiece(int i, int j, int type, int player) {
    return this.placePiece(new PositionState(new Position(i,j), new Piece(type, player)));
  }

  @Override
  public int getWinner() {
    return myWinner;
  }

  @Deprecated
  public Set<Position> getValidMoves() {
    return currentValidMoves;
  }

  public Board setPlayer(int i) {
    Board copy = clone();
    copy.activePlayer = i;
    return copy;
  }

  /**
   * Removes piece at (i,j)
   * Throws exception if either position not in board
   * @param i
   * @param j
   */
  public Board remove(int i, int j) {
    return this.removePiece(new Position(i,j));
  }

  /**
   * Moves piece at (i1,j1) to (i2,j2)
   * Throws exception if either position not in board
   * @param i1
   * @param j1
   * @param i2
   * @param j2
   * @throws OutOfBoardException
   */
  public Board move(int i1, int j1, int i2, int j2) {
    return this.movePiece(new Position(i1,j1), new Position(i2,j2));
  }

  // TODO: implement this
  // LEAVE COMMENTED OUT UNTIL IMPLEMENTED
//  @Override
//  public boolean equals(Object o) {
//    if(o.getClass().equals(Board.class)) {
//      return false;
//    }
//    else {
//
//    }


  /**
   * Returns Piece object at i, j
   * Throws exception if location not in board
   * @param i
   * @param j
   * @return
   */
  public Piece getPiece(int i, int j) {
    return myBoard.get(new Position(i, j)).getOrElseThrow(() -> new OutOfBoardException(
        INVALID_POSITION)).piece();
  }
}
