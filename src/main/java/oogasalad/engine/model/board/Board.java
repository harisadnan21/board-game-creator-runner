package oogasalad.engine.model.board;

import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import org.jooq.lambda.Seq;

/**
 * Class That defines the backend board and defines methods that can be applied to it.
 * @author Jake Heller, Haris Adnan, Robert Cranston, Alex Bildner
 */
@EqualsAndHashCode
@ToString
public class Board implements Iterable<PositionState>, Cloneable, ImmutableBoard{

  public static final String INVALID_POSITION = "Invalid Position";
  private int activePlayer;

  //TODO: update code to use these constants instead of magic numbers

  private final int numRows;
  private final int numCols;
  private static final int FIRST_ROW = 0;
  private static final int FIRST_COL = 0;
  private final int lastRow;
  private final int lastCol;

  private SortedMap<Position, PositionState> myBoard;

  public Board(final PositionState[][] positionStates) {
    this.numRows = positionStates.length;
    this.numCols = getNumColumnsInLongestRow(positionStates);
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
                              .map(tuple -> new Position(tuple.v1, tuple.v2));
    Map<Position, PositionState> map = positions.toMap(pos -> pos,
                                                       pos -> positionStates[pos.row()][pos.column()] == null ? new PositionState(pos, Piece.EMPTY) : positionStates[pos.row()][pos.column()]);
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

  /**
   * Method that removes a piece from the board and returns a new board with this property changed
   * @param position : position where the piece is to be remoced from
   * @return : new board
   */
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

  /**
   * Places a piece at the specified location and returns the new board with this updated feature
   * @param positionState : the position state of the position on the board
   * @return : new board
   */
  public Board placePiece(PositionState positionState) {
    throwIfInvalid(positionState.position());
    Board returnBoard = this.clone();
    returnBoard.myBoard = returnBoard.myBoard.put(positionState.position(), positionState);
    return returnBoard;
  }

  public Seq<Position> getPositions() {
    return Seq.seq(myBoard.values().map(PositionState::position));
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

  public PositionState getPositionStateAt(Position position) {
    return this.getPositionStateAt(position.row(), position.column());
  }


  private static int getNumColumnsInLongestRow(PositionState[][] positionStates) {
    return Arrays.stream(positionStates).mapToInt(Array::getLength).max().orElse(0);
  }


  public boolean isOccupied(int row, int column) {
    PositionState positionState = myBoard.get(new Position(row, column)).getOrElseThrow(() -> new OutOfBoardException("Invalid"));
    return positionState.isPresent();
  }

  /**
   * checks if the x and y coordinates given are valid or not
   * @param x :  x coordinate to be checked
   * @param y : y coordinate to be checked
   * @return: true or false depending on whether the position is valid
   */
  public boolean isValidPosition(int x, int y) {
    return isValidRow(x) && isValidColumn(y);
  }
  /**
   * checks if the position given is valid or not (existent on the board)
   * @param: position to be checked
   * @return: true or false depending on whether the position is valid
   */
  public boolean isValidPosition(Position position) {
    return isValidRow(position.row()) && isValidColumn(position.column());
  }

  /**
   * method to acquire number of rows of board
   * @return
   */
  public int getHeight() {
    return numRows;
  }

  /**
   * Method to acquire number of columns in board
   * @return
   */
  public int getWidth() {
    return numCols;
  }

  /**
   * tells if the row number is valid (contained in the board)
   * @param row : row number
   * @return : true or false
   */
  public boolean isValidRow(int row) {
    return (row >= FIRST_ROW) && (row <= lastRow);
  }
  /**
   * tells if the column number is valid (contained in the board)
   * @param column : column number
   * @return : true or false
   */
  public boolean isValidColumn(int column) {
    return (column >= FIRST_COL) && (column <= lastCol);
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

  public Iterator<PositionState> iterator() {
    return getPositionStatesStream().iterator();
  }

  /**
   * returns if the position at the coordinates is empty or not
   * @param i : x coordinate (row number)
   * @param j : y coordinate (column number)
   * @return : true or false
   */
  public boolean isEmpty(int i, int j) {
    return !isOccupied(i,j);
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

  /**
   * makes a new board with the active player changed
   * @param i: player number
   * @return : new board with the appropriate player
   */
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
