package oogasalad.engine.model.board;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

  //TODO: update code to use these constants instead of magic numbers, maybe even make enum?git

  private int numRows;
  @Deprecated  private int numColumns;
  private SortedMap<Position, PositionState> myBoard;

  public Board(PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numColumns = Board.getNumColumnsInLongestRow(positionStates);
    this.myBoard = getPositionPositionStateMap(positionStates);
  }

  public Board(int rows, int columns) {
    this(getEmptyArrayOfPositionStates(rows, columns));
  }

  @Override
  public int getPlayer() {
    return activePlayer;
  }

  @Override
  public Board clone() throws CloneNotSupportedException {
    return (Board) super.clone();
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
    return this.getPositionStateAt(position.x(), position.y());
  }

  private TreeMap<Position, PositionState> getPositionPositionStateMap(
      PositionState[][] positionStates) {
    Seq<Position> coords = Seq.rangeClosed(0, numColumns-1).crossSelfJoin().map(Position::new);
    Map<Position, PositionState> map = coords.stream()
        .collect(Collectors.toMap(coord -> coord, coord -> positionStates[coord.x()][coord.y()]));
    return TreeMap.ofAll(map);
  }

  private static int getNumColumnsInLongestRow(PositionState[][] positionStates) {
    return Arrays.stream(positionStates).mapToInt(Array::getLength).max().orElse(0);
  }

  private static PositionState[][] getEmptyArrayOfPositionStates(int rows, int columns) {
    PositionState[][] positionStates = new PositionState[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        positionStates[i][j] = new PositionState(new Position(i,j), Piece.EMPTY);
      }
    }
    return positionStates;
  }


  @Override
  public boolean hasPieceAtLocation(int row, int column) {
    PositionState positionState = myBoard.get(new Position(row, column)).get();
    return positionState.piece() != Piece.EMPTY;
  }

  @Override
  public boolean isValidPosition(int x, int y) {
    return isValidX(x) && isValidY(y);
  }

  public boolean isValidPosition(Position position) {
    return isValidX(position.x()) && isValidY(position.y());
  }

  private boolean isValidY(int y) {
    return Utilities.isPositive(y) && (y <= numColumns);
  }

  private boolean isValidX(int x) {
    return Utilities.isPositive(x) && (x <= numRows);
  }


  @Override
  public int getHeight() {
    return numRows;
  }

  @Override
  public int getWidth() {
    return numColumns;
  }

  @Override
  public boolean isValidRow(int row) {
    return isValidY(row);
  }

  @Override
  public boolean isValidColumn(int column) {
    return isValidX(column);
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
  public PositionState getPositionStateAt(int x, int y) {
    return myBoard.get(new Position(x, y)).get();
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

  public boolean isEmpty(int x, int y) {
    return !hasPieceAtLocation(x,y);
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
