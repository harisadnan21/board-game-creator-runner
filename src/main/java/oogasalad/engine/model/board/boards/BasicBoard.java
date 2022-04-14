package oogasalad.engine.model.board.boards;

import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;
import oogasalad.engine.model.board.components.Piece;
import oogasalad.engine.model.board.components.Position;
import oogasalad.engine.model.board.components.PositionState;
import oogasalad.engine.model.board.misc.OutOfBoardException;
import org.jooq.lambda.Seq;

public class BasicBoard {

  public static final String INVALID_POSITION = "Invalid Position";
  private static final int FIRST_ROW = 0;
  private static final int FIRST_COL = 0;
  protected final int numRows;
  protected final int numCols;
  protected final int lastRow;
  protected final int lastCol;
  protected SortedMap<Position, PositionState> myBoard;

  public BasicBoard(int rows, int columns) {
    this.numRows = rows;
    this.numCols = columns;
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    PositionState[][] positionStates = getEmptyArrayOfPositionStates();
    this.myBoard = getPositionStatesMap(positionStates);
  }

  public BasicBoard(PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numCols = Board.getNumColumnsInLongestRow(positionStates);
    this.lastRow = numRows - 1;
    this.lastCol = numCols - 1;
    this.myBoard = getPositionStatesMap(positionStates);
  }

  protected static int getNumColumnsInLongestRow(PositionState[][] positionStates) {
    return Arrays.stream(positionStates).mapToInt(Array::getLength).max().orElse(0);
  }

  protected TreeMap<Position, PositionState> getPositionStatesMap(
      PositionState[][] positionStates) {
    Seq<Position> positions = Seq.rangeClosed(FIRST_ROW, lastRow)
        .crossJoin(Seq.rangeClosed(FIRST_COL, lastCol))
        .map(Position::new);
    Map<Position, PositionState> map = positions.toMap(pos -> pos,
        pos -> positionStates[pos.i()][pos.j()]);
    return TreeMap.ofAll(map);
  }

  protected PositionState[][] getEmptyArrayOfPositionStates() {
    PositionState[][] positionStates = new PositionState[numRows][numCols];

    for (int i = FIRST_ROW; i <= lastRow; i++) {
      for (int j = FIRST_COL; j <= lastCol; j++) {
        positionStates[i][j] = new PositionState(new Position(i, j), Piece.EMPTY);
      }
    }
    return positionStates;
  }

  public PositionState getPositionStateAt(Position position) {
    return this.getPositionStateAt(position.i(), position.j());
  }

  public boolean hasPieceAtLocation(int row, int column) {
    PositionState positionState = myBoard.get(new Position(row, column))
        .getOrElseThrow(() -> new OutOfBoardException("Invalid"));
    return !positionState.piece().equals(Piece.EMPTY);
  }

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

  public int getHeight() {
    return numRows;
  }

  public int getWidth() {
    return numCols;
  }

  public boolean isValidRow(int row) {
    return isValidI(row);
  }

  public boolean isValidColumn(int column) {
    return isValidJ(column);
  }

  public Stream<PositionState> getPositionStatesStream() {
    return myBoard.values().toJavaStream();
  }

  public Seq<PositionState> getPositionStatesSeq() {
    return Seq.seq(myBoard.values().toJavaStream());
  }

  public PositionState getPositionStateAt(int i, int j) {
    return myBoard.get(new Position(i, j)).getOrElseThrow(() -> new OutOfBoardException(
        INVALID_POSITION));
  }

  public boolean isEmpty(int i, int j) {
    return !hasPieceAtLocation(i, j);
  }

  /**
   * Returns Piece object at i, j Throws exception if location not in board
   *
   * @param i
   * @param j
   * @return
   */
  public Piece getPiece(int i, int j) {
    return myBoard.get(new Position(i, j)).getOrElseThrow(() -> new OutOfBoardException(
        INVALID_POSITION)).piece();
  }
}
