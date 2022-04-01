package oogasalad.engine.model.board;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import oogasalad.engine.model.driver.Observable;
import oogasalad.engine.model.misc.Piece;
import oogasalad.engine.model.utilities.Utilities;
import io.vavr.collection.TreeMap;
import io.vavr.collection.SortedMap;
import org.jooq.lambda.Seq;

public class Board extends Observable<Piece[][]> implements Cloneable, Iterable<PositionState> {

  private int numRows;
  private int numColumns;
  private SortedMap<Position, PositionState> myBoard;

  public Board(PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numColumns = Board.getNumColumnsInLongestRow(positionStates);
    this.myBoard = getPositionPositionStateMap(positionStates);
  }
  @Override
  protected Board clone() throws CloneNotSupportedException {
    return (Board) super.clone();
  }

  public Board removePiece(Position position) throws CloneNotSupportedException {
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(position, new PositionState(position, null, null));
    return returnBoard;
  }

  public Board placePiece(PositionState positionState) throws CloneNotSupportedException {
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(positionState.position(), positionState);
    return returnBoard;
  }

  public Board movePiece(Position oldPosition, Position newPosition) throws CloneNotSupportedException {
    PositionState oldPositionState = this.getPositionStateAt(oldPosition);
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(oldPosition, new PositionState(oldPosition, null, null)).put(newPosition, new PositionState(newPosition, oldPositionState.player(), oldPositionState.pieceType()));
    return returnBoard;
  }

  private PositionState getPositionStateAt(Position position) {
    return this.getPositionStateAt(position.x(), position.y());
  }

  private TreeMap<Position, PositionState> getPositionPositionStateMap(
      PositionState[][] positionStates) {
    Seq<Position> coords = Seq.rangeClosed(1, numColumns).crossSelfJoin().map(Position::new);
    Map<Position, PositionState> map = coords.stream()
        .collect(Collectors.toMap(coord -> coord, coord -> positionStates[coord.x()][coord.y()]));
    return TreeMap.ofAll(map);
  }

  public static int getNumColumnsInLongestRow(PositionState[][] positionStates) {
    return Arrays.stream(positionStates).mapToInt(Array::getLength).max().getAsInt();
  }

  public Board(int rows, int columns) {
    this(getEmptyArrayOfPositionStates(rows, columns));
  }

  public static PositionState[][] getEmptyArrayOfPositionStates(int rows, int columns) {
    PositionState[][] positionStates = new PositionState[columns][rows];
    for (PositionState[] positionState : positionStates) {
      for (PositionState positionState1 : positionState) {
        positionState1 = new PositionState(null, null, null);
      }
    }
    return positionStates;
  }


  /**
   * returns true if there is a piece at location Board[row][column]. else, false
   *
   * @param row
   * @param column
   * @return
   */
  public Optional<Boolean> isPieceAtLocation(int row, int column) {
    return isPieceAtCoordinate(column, row);
  }

  public Optional<Boolean> isPieceAtCoordinate(int x, int y) {
    PositionState positionState = myBoard.get(new Position(x, y)).getOrNull();
    if (positionState == null) {
      return Optional.empty();
    }
    return Optional.of(positionState.pieceType() == null);
  }

  public boolean isValid(int x, int y) {
    return isValidX(x) && isValidY(y);
  }

  public boolean isValidY(int y) {
    return Utilities.isPositive(y) && y <= this.numRows;
  }

  public boolean isValidX(int x) {
    return Utilities.isPositive(x) && x <= this.numColumns;
  }

  public Boolean isValid(Position position) {
    return isValid(position.x(), position.y());
  }

  public Boolean isValidRow(int row) {
    return isValidY(row);
  }

  public Boolean isValidColumn(int column) {
    return isValidX(column);
  }

  @Override
  public Iterator<PositionState> iterator() {
    return myBoard.valuesIterator();
  }

  public Stream<PositionState> getPositionStatesStream() {
    return myBoard.values().toJavaStream();
  }

  public Stream<PositionState> getSatisfyingPositionStatesStream(Predicate<PositionState> positionStatePredicate){
    return getPositionStatesStream().filter(positionStatePredicate);
  }

  public Stream<PositionState> getNotSatisfyingPositionStatesStream(Predicate<PositionState> positionStatePredicate){
    return myBoard.values().filterNot(positionStatePredicate).toJavaStream();
  }

  public PositionState getPositionStateAt(int x, int y){
    return myBoard.get(new Position(x,y)).get();
  }





//  public void selectCell(int x, int y){
//    Piece[][] oldBoard = myBoard;
//    if (myBoard[x][y] != null) {
//      myBoard[x][y] = null;
//    }
//    else {
//      place(x, y, new Piece("...", 1));
//    }
//    notifyListeners("UPDATE", oldBoard, myBoard);
//  }

//  public void placeNewPiece(int row, int column, Piece piece){
//    place(row, column, piece);
//  }
//  private void place(int i, int j, Piece piece){
//    myBoard[i][j] = piece;
//  }

//  public void remove(int i, int j){myBoard[i][j] =null;};

//  public Piece getPiece(int i, int j) {
//    //return Optional.of(myBoard[i][j]);
//    return myBoard[i][j];
//  }

//  /**
//   *
//   * @param i end i position
//   * @param j end j position
//   * @param piece
//   */
//  public void move(int i, int j, Piece piece) {
//    if (!isPieceAtLocation(i,j)){
//      place(i, j, piece);
//      remove(i, j);
//    }

//  public Board deepCopy() {
//    Board board = new Board(myRows, myColumns);
//    for (Pair<Position, Piece> piece: this) {
//      Piece copyPiece;
//      if (piece.getValue() == null) {
//        copyPiece = null;
//      }
//      else {
//        copyPiece = piece.getValue().deepCopy();
//      }
//      board.place(piece.getKey().getI(), piece.getKey().getJ(), copyPiece);
//    }
//    return board;
//  }
//
//  // Let's discuss, I think we should use the Java Streams class to create a Stream over the board declaratively, because:
// 1. We can use built in functionality for streams
// 2. Very easy to make code parallel/concurrent
// 3. Open-Closed -> we won't have to change implemenation if we decide to change how to represent Board because it will still be a Stream
//  @Override
//  public Iterator<Pair<Position, Piece>> iterator() {
//    return new BoardIterator(myBoard);
//  }

}

