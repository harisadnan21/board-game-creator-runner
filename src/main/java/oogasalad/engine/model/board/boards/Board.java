package oogasalad.engine.model.board.boards;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import oogasalad.engine.model.board.components.Piece;
import oogasalad.engine.model.board.components.Position;
import oogasalad.engine.model.board.components.PositionState;
import oogasalad.engine.model.board.misc.OutOfBoardException;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;

/**
 * Class That defines the backend board and defines methods that can be applied to it.
 * @author Jake Heller, Haris Adnan, Robert Cranston, Alex Bildner
 */
//@EqualsAndHashCode
//@ToString
public class Board extends BasicBoard implements DisplayableBoard {

  public static final int NO_WINNER_YET = -2; //Eh
  private int activePlayer;
  private Set<Position> currentValidMoves; //Why does the Board care?
  private int myWinner = NO_WINNER_YET; //Why does the Board care?


  //TODO: update code to use these constants instead of magic numbers

  public Board(PositionState[][] positionStates) {
    super(positionStates);
//    this.numRows = positionStates.length;
//    this.numCols = Board.getNumColumnsInLongestRow(positionStates);
//    this.lastRow = numRows - 1;
//    this.lastCol = numCols - 1;
//    this.myBoard = getPositionStatesMap(positionStates);
  }

  public Board(int rows, int columns) {
    super(rows, columns);
//    this.numRows = rows;
//    this.numCols = columns;
//    this.lastRow = numRows - 1;
//    this.lastCol = numCols - 1;
//    PositionState[][] positionStates = getEmptyArrayOfPositionStates();
//    this.myBoard = getPositionStatesMap(positionStates);
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
    PositionState oldPositionState = this.getPositionStateAt(oldPosition);
    Board returnBoard = this.clone();

    PositionState newPositionState = new PositionState(newPosition, oldPositionState.piece());

    returnBoard.myBoard = returnBoard.myBoard.put(oldPosition, new PositionState(oldPosition, Piece.EMPTY)).put(newPosition, newPositionState);
    return returnBoard;
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

  @Deprecated
  public void setValidMoves(Set<Position> moves) {
    currentValidMoves = moves;
  }

  public void setWinner(int winner) {
    myWinner = winner;
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
  public Board removePiece(int i, int j) {
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
  public Board movePiece(int i1, int j1, int i2, int j2) {
    return this.movePiece(new Position(i1,j1), new Position(i2,j2));
  }


}
