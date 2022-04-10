package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import oogasalad.engine.model.OutOfBoardException;
import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;

import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
//import oogasalad.engine.model.utilities.Utilities;
import oogasalad.engine.model.Utilities;
import org.jooq.lambda.Seq;

public class PersistentMapBoard implements Board {

  public static final int NO_WINNER_YET = -2; //Eh
  private int activePlayer; //Why does the Board care?
  private Set<Position> currentValidMoves; //Why does the Board care?
  private int myWinner = NO_WINNER_YET; //Why does the Board care?

  private int myWidth;
  private int myHeight;

  private final int numRows;
  private final int numCols;
  private final int firstRow = 0;
  private final int firstCol = 0;
  private final int lastRow;
  private final int lastCol;
  private SortedMap<Position, PositionState> myPieces;


  @Override
  public Board placeNewPiece(int i, int j, int type, int player){
    Position pos = new Position(i,j);
    Piece piece = new Piece(type, player);
    PositionState state = new PositionState(pos, piece);
    return placePiece(state);
  }

  @Override
  public Board remove(int i, int j) {
    try {
      return removePiece(new Position(i, j));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean isEmpty(int i, int j) {
    return !hasPieceAtLocation(i,j);
  }

  @Override
  public Board move(int i1, int j1, int i2, int j2) {
    return movePiece(new Position(i1,j1), new Position(i2,j2));
  }

  @Override
  public void setPlayer(int player) {
    activePlayer = player;
  }

  @Override
  public void setValidMoves(Set<Position> validMoves) {

  }

  @Override
  public Set<Position> getValidMoves() {
    return null;
  }

  @Override
  public void setWinner(int winner) {

  }

  @Override
  public int getWinner() {
    return 0;
  }


  //TODO: update code to use these constants instead of magic numbers


  public PersistentMapBoard(PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numCols = positionStates[0].length;
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    this.myPieces = getPositionStatesMap(positionStates);
  }

  public PersistentMapBoard(int rows, int columns) {
    this.numRows = rows;
    this.numCols = columns;
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    PositionState[][] positionStates = getEmptyArrayOfPositionStates();
    this.myPieces = getPositionStatesMap(positionStates);
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
  public PersistentMapBoard clone() {
    PersistentMapBoard board = new PersistentMapBoard(myHeight, myWidth);
    board.myPieces = myPieces;
    board.activePlayer = activePlayer;
    return board;
  }

  public Board copy() {
    return this.clone();
  }

  private PersistentMapBoard cloneMapBoard() throws CloneNotSupportedException {
    return clone();
  }


  public Board removePiece(Position position) {
    PersistentMapBoard returnBoard = (PersistentMapBoard) this.clone();
    returnBoard.myPieces = returnBoard.myPieces.put(position, new PositionState(position, Piece.EMPTY));
    return returnBoard;
  }

  private Board placePiece(PositionState positionState) {
    PersistentMapBoard returnBoard = clone();
    returnBoard.myPieces = returnBoard.myPieces.put(positionState.position(), positionState);
    return returnBoard;
  }

  private Board movePiece(Position oldPosition, Position newPosition) {

    PositionState oldPositionState = this.getPositionStateAt(oldPosition);
    PersistentMapBoard returnBoard = this.clone();

    PositionState newPositionState = new PositionState(newPosition, oldPositionState.piece());

    returnBoard.myPieces = returnBoard.myPieces.put(oldPosition, new PositionState(oldPosition, Piece.EMPTY)).put(newPosition, newPositionState);
    return returnBoard;
  }

  private PositionState getPositionStateAt(Position position) {
    return myPieces.get(position).get();
  }

  @Override
  public PositionState getPositionStateAt(int i, int j) {
    return getPositionStateAt(new Position(i,j));
  }

  private Piece getPiece(int i, int j) {
    if (!isValidPosition(i,j)) {
      throwOutOfBoardError(i,j);
      return null;
    }
    else if (!hasPieceAtLocation(i,j)) {
      return Piece.EMPTY;
    }
    else {
      return myPieces.get(new Position(i,j)).get().piece();
    }
  }


  private static int getNumColumnsInLongestRow(PositionState[][] positionStates) {
    return Arrays.stream(positionStates).mapToInt(Array::getLength).max().orElse(0);
  }

//  public boolean hasPieceAtLocation(int i, int j) {
//    PositionState positionState = myPieces.get(new Position(i, j)).get();
//    return positionState.piece() != Piece.EMPTY;
//  }


  @Override
  public boolean hasPieceAtLocation(int row, int column) {
    PositionState positionState = myPieces.get(new Position(row, column)).get();
    return positionState.piece().equals(Piece.EMPTY);
  }

  @Override
  public boolean isValidPosition(int x, int y) {
    return isValidI(x) && isValidJ(y);
  }

  public boolean isValidPosition(Position position) {
    return isValidI(position.i()) && isValidJ(position.j());
  }

  private boolean isValidJ(int j) {
    return Utilities.isPositive(j) && (j <= numCols);
  }

  private boolean isValidI(int i) {
    return Utilities.isPositive(i) && (i <= numRows);
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
  public Iterator<PositionState> iterator() {
    return getPositionStatesStream().iterator();
  }

  public boolean isValidRow(int row) {
    return isValidJ(row);
  }

  public boolean isValidColumn(int column) {
    return isValidI(column);
  }

  public Stream<PositionState> getPositionStatesStream() {
    return myPieces.values().toJavaStream();
  }

//  public Seq<PositionState> getPositionStatesSeq() {
//    return Seq.seq(myPieces.values().toJavaStream());
//  }
//
//  public Stream<PositionState> getSatisfyingPositionStatesStream(
//      Predicate<PositionState> positionStatePredicate) {
//    return getPositionStatesStream().filter(positionStatePredicate);
//  }
//
//  public Stream<PositionState> getNotSatisfyingPositionStatesStream(
//      Predicate<PositionState> positionStatePredicate) {
//    return myPieces.values().filterNot(positionStatePredicate).toJavaStream();
//  }
//
//  public Seq<PositionState> getNotSatisfyingPositionStatesSeq(
//      Predicate<PositionState> positionStatePredicate) {
//    return Seq.seq(this.getNotSatisfyingPositionStatesStream(positionStatePredicate));
//  }
//
//  public Seq<PositionState> getSatisfyingPositionStatesSeq(
//      Predicate<PositionState> positionStatePredicate) {
//    return Seq.seq(this.getSatisfyingPositionStatesStream(positionStatePredicate));
//  }
//
//  public Map<Integer, List<PositionState>> piecesByPlayer(){
//    return getPositionStatesSeq().groupBy(PositionState::player);
//  }
//
//  public Map<Integer,Integer> numPiecesByPlayer(){
//    return Seq.seq(piecesByPlayer())
//        .toMap(pair -> pair.v1, pair -> pair.v2.size());
//  }
}
