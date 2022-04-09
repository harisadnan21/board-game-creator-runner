package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.Utilities;

/**
 * Class That defines the backend board and defines methods that can be applied to it.
 * @author Jake Heller, Haris Adnan, Robert Cranston
 */
public class ArrayBoard implements Board {
  private int myRows;
  private int myColumns;
  private Piece[][] pieceLocations;
  private int activePlayer;
  private Set<Position> currentValidMoves;
  private int myWinner = Board.NO_WINNER_YET;


  public ArrayBoard(int rows, int columns) {
    myRows = rows;
    myColumns = columns;
    pieceLocations = new Piece[rows][columns];
    activePlayer = 0;
  }

  public ArrayBoard(ArrayBoard board) {
    this(board.myRows, board.myColumns);
    activePlayer = board.getPlayer();
    for (PositionState cell: this) {
      Piece piece = cell.piece();
      if (piece != null) {
        this.placeNewPiece(cell.position().i(), cell.position().j(), piece.type(), piece.player());
      }
    }
  }

  /**
   * returns true if there is a piece at location Board[row][column]. else, false
   * @param row
   * @param column
   * @return
   */
  private boolean isLocationOccupied(int row, int column){
    return pieceLocations[row][column] != null;
  }

  @Override
  public Board placeNewPiece(int row, int column, int type, int player) throws OutOfBoardException {
    Piece piece = new Piece(type, player);
    place(row, column, piece);
    return this;
  }

  private void place(int i, int j, Piece piece) throws OutOfBoardException {
    if(isValidPosition(i,j)){
      pieceLocations[i][j] = piece;
    }
    else{
      throw new OutOfBoardException("Piece out of Board");
    }
  }

  @Override
  public Board remove(int i, int j){
    if (!isValidPosition(i, j)) {
      throwOutOfBoardError(i,j);
    }
    pieceLocations[i][j] = null;
    return this;
  }

  @Override
  public boolean isEmpty(int i, int j) {
    if (!isValidPosition(i, j)){
      throwOutOfBoardError(i,j);
    }
    return pieceLocations[i][j] == null;
  }

  @Override
  public Optional<Piece> getPieceRecord(int i, int j) {
    //return Optional.of(myBoard[i][j]);
    if (!isValidPosition(i,j)) {
      throwOutOfBoardError(i,j);
    }
    Optional<Piece> piece;
    if (pieceLocations[i][j] == null) {
      piece = Optional.empty();
    }
    else {
      piece = Optional.of(pieceLocations[i][j]);
    }
    return piece;
  }


  /**
   * If piece exists at (i1, j1), moves that piece
   * to (i2, j2)
   * @param i1
   * @param j1
   * @param i2
   * @param j2
   * @throws OutOfBoardException
   */
  @Override
  public Board move(int i1, int j1, int i2, int j2) throws OutOfBoardException {
    if (!isValidPosition(i1,j1)) {
      throwOutOfBoardError(i1,j1);
    }
    else if (!isValidPosition(i2,j2)) {
      throwOutOfBoardError(i2,j2);
    }
    if (!isEmpty(i1,j1)){
      Piece piece = pieceLocations[i1][j1];
      place(i2, j2, piece);
      pieceLocations[i1][j1] = null;
    }
    return this;
  }

  @Override
  public void setPlayer(int player) {
    activePlayer = player;
  }

  @Override
  public int getPlayer() {
    return activePlayer;
  }

  @Override
  public boolean isValidPosition(int i, int j) {
    return isValidX(i) && isValidY(j);
  }

  @Override
  public boolean isValidPosition(Position position){
    return isValidX(position.i()) && isValidY(position.j());
  }

  private boolean isValidY(int j) {
    return Utilities.isPositive(j) && (j < myRows);
  }

  private boolean isValidX(int i) {
    return Utilities.isPositive(i) && (i < myColumns);
  }

  @Override
  public ArrayBoard copy() throws OutOfBoardException {
    ArrayBoard board = new ArrayBoard(myRows, myColumns);
    board.setPlayer(this.getPlayer());
    for (PositionState cell: this) {
      Piece piece = cell.piece();
      if (piece != null) {
        board.placeNewPiece(cell.position().i(), cell.position().j(), piece.type(), piece.player());
      }
    }
    return board;
  }

  /**
   * Sets the valid moves for the currently selected piece on the board or null if no piece is selected
   * @param validMoves Set of Position values of valid moves for selected cell
   */
  @Override
  public void setValidMoves(Set<Position> validMoves) { currentValidMoves = validMoves; }

  /**
   * Returns the Set of Positions of valid moves of selected piece
   * @return Set of Positions of valid moves of selected piece
   */
  @Override
  public Set<Position> getValidMoves() { return currentValidMoves; }

  /**
   * Sets the winner of the board. Only called when game is over in checkForWin Method
   * @see oogasalad.engine.model.engine.PieceSelectionEngine
   * @param winner int representing player that wins the game
   */
  @Override
  public void setWinner(int winner){myWinner =  winner;}

  /**
   * Returns the winner of the game
   * @see oogasalad.engine.view.BoardView
   * @return winner based on current board
   */
  @Override
  public int getWinner(){return myWinner;}

  // Let's discuss, I think we should use the Java Streams class to create a Stream over the board declaratively, because:
// 1. We can use built in functionality for streams
// 2. Very easy to make code parallel/concurrent
// 3. Open-Closed -> we won't have to change implemenation if we decide to change how to represent Board because it will still be a Stream
  @Override
  public Iterator<PositionState> iterator() {
    return new BoardIterator(pieceLocations);
  }

  @Override
  public int getHeight() {
    return myRows;
  }

  @Override
  public int getWidth() {
    return myColumns;
  }

}
