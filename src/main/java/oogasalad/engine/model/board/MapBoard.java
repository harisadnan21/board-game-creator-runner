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
import org.jooq.lambda.Seq;

/**
 * Class That defines the backend board and defines methods that can be applied to it.
 * @author Jake Heller, Haris Adnan, Robert Cranston, Alex Bildner
 */
public class MapBoard implements Board {

  public static final int NO_WINNER_YET = -2; //Eh
  private int activePlayer; //Why does the Board care?
  private Set<Position> currentValidMoves; //Why does the Board care?
  private int myWinner = NO_WINNER_YET; //Why does the Board care?


  //TODO: update code to use these constants instead of magic numbers

  private final int numRows;
  private final int numCols;
  private final int firstRow = 0;
  private final int firstCol = 0;
  private final int lastRow;
  private final int lastCol;
  private SortedMap<Position, PositionState> myBoard;

  public MapBoard(PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numCols = MapBoard.getNumColumnsInLongestRow(positionStates);
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    this.myBoard = getPositionStatesMap(positionStates);
  }

  public MapBoard(int rows, int columns) {
    this.numRows = rows;
    this.numCols = columns;
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    PositionState[][] positionStates = getEmptyArrayOfPositionStates();
    this.myBoard = getPositionStatesMap(positionStates);
  }

  private TreeMap<Position, PositionState> getPositionStatesMap(
      PositionState[][] positionStates) {
    Seq<Position> positions = Seq.rangeClosed(firstRow, lastRow)
        .crossJoin(Seq.rangeClosed(firstCol, lastCol))
        .map(Position::new);
    Map<Position, PositionState> map = positions.toMap(pos -> pos
        ,pos -> positionStates[pos.i()][pos.j()]);
    return TreeMap.ofAll(map);
  }

  private PositionState[][] getEmptyArrayOfPositionStates() {
    PositionState[][] positionStates = new PositionState[numRows][numCols];

    for (int i = firstRow; i <= lastRow; i++) {
      for (int j = firstCol; j <= lastCol; j++) {
        positionStates[i][j] = new PositionState(new Position(i,j), Piece.EMPTY);
      }
    }
    return positionStates;
  }

  @Override
  public int getPlayer() {
    return activePlayer;
  }

  @Override
  public MapBoard clone() {
    try {
      return (MapBoard) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("bad");
    }

  }

  public Board copy() {
    return this.clone();
  }

  @Override
  public void setValidMoves(Set<Position> validMoves) {

  }


  public Board removePiece(Position position) {
    MapBoard returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(position, new PositionState(position, Piece.EMPTY));
    return returnBoard;
  }

  public Board placePiece(PositionState positionState) {
    MapBoard returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(positionState.position(), positionState);
    return returnBoard;
  }

  public Board movePiece(Position oldPosition, Position newPosition) {

    PositionState oldPositionState = this.getPositionStateAt(oldPosition);
    MapBoard returnBoard = this.clone();

    PositionState newPositionState = new PositionState(newPosition, oldPositionState.piece());

    returnBoard.myBoard = returnBoard.myBoard.put(oldPosition, new PositionState(oldPosition, Piece.EMPTY)).put(newPosition, newPositionState);
    return returnBoard;
  }

  private PositionState getPositionStateAt(Position position) {
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
  public boolean hasPieceAtLocation(int i, int j) {
    PositionState positionState = myBoard.get(new Position(i, j)).get();
    return positionState.isPresent();
  }

  @Override
  public boolean isValidPosition(int x, int y) {
    return isValidI(x) && isValidJ(y);
  }

  public boolean isValidPosition(Position position) {
    return isValidI(position.i()) && isValidJ(position.j());
  }

  private boolean isValidJ(int j) {
    return (j >= firstCol) && (j <= lastCol);
  }

  private boolean isValidI(int i) {
    return (i >= firstRow) && (i <= lastRow);
  }


  @Override
  public int getHeight() {
    return numRows;
  }

  @Override
  public int getWidth() {
    return numCols;
  }

  public boolean isValidRow(int row) {
    return isValidJ(row);
  }

  public boolean isValidColumn(int column) {
    return isValidI(column);
  }

  public Stream<PositionState> getPositionStatesStream() {
    return myBoard.values().toJavaStream();
  }

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

//  public Map<Integer, List<PositionState>> piecesByPlayer(){
//    return getPositionStatesSeq().groupBy(PositionState::player);
//  }
//
//  public Map<Integer,Integer> numPiecesByPlayer(){
//    return Seq.seq(piecesByPlayer())
//        .toMap(pair -> pair.v1, pair -> pair.v2.size());
//  }
//
  @Override
  public PositionState getPositionStateAt(int i, int j) {
    return myBoard.get(new Position(i, j)).get();
  }
//
//  @Override
//  public Map<Integer, List<PositionState>> getRows() {
//    return getPositionStatesSeq().groupBy(PositionState::i);
//  }
//
//  @Override
//  public Map<Integer, List<PositionState>> getCols() {
//    return getPositionStatesSeq().groupBy(PositionState::j);
//  }

  @Override
  public Iterator<PositionState> iterator() {
    return getPositionStatesStream().iterator();
  }

  public boolean isEmpty(int i, int j) {
    return !hasPieceAtLocation(i,j);
  }

  @Deprecated
  public void setValidMoves(Object o) {
  }

  public void setWinner(int winner) {
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
    return 0;
  }

  @Deprecated
  public Set<Position> getValidMoves() {
    return null;
  }

  @Deprecated
  public Board deepCopy() {
    return null;
  }

  public void setPlayer(int i) {
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
   */
  public Board move(int i1, int j1, int i2, int j2) {
    return this.movePiece(new Position(i1,j1), new Position(i2,j2));
  }

}
