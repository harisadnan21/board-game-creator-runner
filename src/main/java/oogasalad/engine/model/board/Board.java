package oogasalad.engine.model.board;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import oogasalad.engine.model.utilities.Utilities;
import io.vavr.collection.TreeMap;
import io.vavr.collection.SortedMap;
import org.jooq.lambda.Seq;

/**
 * Class That defines the backend board and defines methods that can be applied to it.
 * @author Jake Heller, Haris Adnan, Robert Cranston, Alex Bildner
 */
public class Board implements DisplayableBoard {

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
  public Board clone() throws CloneNotSupportedException {
    return (Board) super.clone();
  }

  public Board copy() throws CloneNotSupportedException {
    return this.clone();
  }


  public Board removePiece(Position position) throws CloneNotSupportedException {
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(position, new PositionState(position, Piece.EMPTY));
    return returnBoard;
  }

  public Board placePiece(PositionState positionState) throws CloneNotSupportedException {
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(positionState.position(), positionState);
    return returnBoard;
  }

  public Board movePiece(Position oldPosition, Position newPosition)
      throws CloneNotSupportedException {

    PositionState oldPositionState = this.getPositionStateAt(oldPosition);
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
    PositionState positionState = myBoard.get(new Position(row, column)).get();
    return positionState.piece() != Piece.EMPTY;
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

  @Override
  public boolean isValidRow(int row) {
    return isValidJ(row);
  }

  @Override
  public boolean isValidColumn(int column) {
    return isValidI(column);
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

  public Map<Integer, List<PositionState>> piecesByPlayer(){
    return getPositionStatesSeq().groupBy(PositionState::player);
  }

  public Map<Integer,Integer> numPiecesByPlayer(){
    return Seq.seq(piecesByPlayer())
              .toMap(pair -> pair.v1, pair -> pair.v2.size());
  }

  @Override
  public PositionState getPositionStateAt(int i, int j) {
    return myBoard.get(new Position(i, j)).get();
  }

  // TODO: implement
  @Override
  public Collection<Stream<PositionState>> getRows() {
    return null;
  }

  // TODO: implement
  @Override
  public Collection<Stream<PositionState>> getCols() {
    return null;
  }

  @Override
  public Iterator<PositionState> iterator() {
    return getPositionStatesStream().iterator();
  }

  public boolean isEmpty(int i, int j) {
    return !hasPieceAtLocation(i,j);
  }

  public void setValidMoves(Object o) {
  }

  public void setWinner(int winner) {
  }

  public void placeNewPiece(int i, int j, int i1, int i2) {
  }

  @Override
  public int getWinner() {
    return 0;
  }

  public Position[] getValidMoves() {
    return null;
  }

  public Board deepCopy() {
    return null;
  }

  public void setPlayer(int i) {
  }

  public void remove(int i, int i1) {
  }

  public void move(int i, int i1, int i2, int i3) {
  }

  @Override
  public Map<Object, Object> getPieceRecord(int x, int y) {
    return null;
  }
}
