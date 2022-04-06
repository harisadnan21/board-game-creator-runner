package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.Utilities;

public class Board implements Iterable<Pair<Position, Piece>> {
  public static final int NO_WINNER_YET = -2;
  private int myRows;
  private int myColumns;
  private Piece[][] pieceLocations;
  private int activePlayer;
  private Set<Position> currentValidMoves;
  private int myWinner = NO_WINNER_YET;


  public Board(int rows, int columns) {
    myRows = rows;
    myColumns = columns;
    pieceLocations = new Piece[rows][columns];
    activePlayer = 0;
  }

  public Board(Board board) {
    this(board.myRows, board.myColumns);
    activePlayer = board.getPlayer();
    for (Pair<Position, Piece> pair: board) {
      if (pair.getValue() != null) {
        Piece piece = pair.getValue();
        try {
          this.placeNewPiece(piece.getPieceRecord().rowNum(), piece.getPieceRecord().colNum(),
              piece.getPieceRecord().type(), piece.getPieceRecord().player());
        } catch (OutOfBoardException e) {
          // since only considering pieces in board, exception is not relevant
        }
      }
    }
  }

  public Piece[][] getMyBoard(){
    return pieceLocations;
  }

  /**
   * returns true if there is a piece at location Board[row][column]. else, false
   * @param row
   * @param column
   * @return
   */
  private boolean isPieceAtLocation(int row, int column){
    return pieceLocations[row][column] != null;

  }
  public void placeNewPiece(int row, int column, int type, int player) throws OutOfBoardException {
    Piece piece = new Piece(type, player, row, column);
    place(row, column, piece);
  }

  private void place(int i, int j, Piece piece) throws OutOfBoardException {
    if(i <= myRows && j <= myColumns){
      if (piece != null) {
        piece.movePiece(i, j);
      }
      pieceLocations[i][j] = piece;
    }
    else{
      throw new OutOfBoardException("Piece out of Board");
    }
  }

  public void remove(int i, int j){
    if (!isValid(i, j)) {
      throwOutOfBoardError(i,j);
    }
    pieceLocations[i][j] = null;
  }

  public boolean isEmpty(int i, int j) {
    if (!isValid(i, j)){
      throwOutOfBoardError(i,j);
    }
    return pieceLocations[i][j] == null;
  }

  public void throwOutOfBoardError(int i, int j) {
    throw new OutOfBoardException(String.format("Index (%d,%d) out of bounds", i, j));
  }

  public Optional<PieceRecord> getPieceRecord(int i, int j) {
    //return Optional.of(myBoard[i][j]);
    if (!isValid(i,j)) {
      throwOutOfBoardError(i,j);
    }
    Optional<PieceRecord> piece;
    if (pieceLocations[i][j] == null) {
      piece = Optional.empty();
    }
    else {
      piece = Optional.of(pieceLocations[i][j].getPieceRecord());
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
  public void move(int i1, int j1, int i2, int j2) throws OutOfBoardException {
    if (!isValid(i1,j1)) {
      throwOutOfBoardError(i1,j1);
    }
    else if (!isValid(i2,j2)) {
      throwOutOfBoardError(i2,j2);
    }
    if (isPieceAtLocation(i1,j1)){
      Piece piece = pieceLocations[i1][j1];
      place(i2, j2, piece);
      pieceLocations[i1][j1] = null;
    }
  }

  public void setPlayer(int player) {
    activePlayer = player;
  }

  public int getPlayer() {
    return activePlayer;
  }

  public boolean isValid(int i, int j) {
    return isValidX(i) && isValidY(j);
  }

  public boolean isValid(Position position){
    return isValidX(position.i()) && isValidY(position.j());
  }

  private boolean isValidY(int j) {
    return Utilities.isPositive(j) && (j < myRows);
  }

  private boolean isValidX(int i) {
    return Utilities.isPositive(i) && (i < myColumns);
  }

  public Board deepCopy() throws OutOfBoardException {
    Board board = new Board(myRows, myColumns);
    board.setPlayer(this.getPlayer());
    for (Pair<Position, Piece> pair: this) {
      Piece copyPiece;
      if (pair.getValue() != null) {
        Piece piece = pair.getValue();
        board.placeNewPiece(piece.getPieceRecord().rowNum(), piece.getPieceRecord().colNum(), piece.getPieceRecord()
            .type(), piece.getPieceRecord().player());
      }
    }
    return board;
  }

  /**
   * Sets the valid moves for the currently selected piece on the board or null if no piece is selected
   * @param validMoves Set of Position values of valid moves for selected cell
   */
  public void setValidMoves(Set<Position> validMoves) { currentValidMoves = validMoves; }

  /**
   * Returns the Set of Positions of valid moves of selected piece
   * @return Set of Positions of valid moves of selected piece
   */
  public Set<Position> getValidMoves() { return currentValidMoves; }

  public void setWinner(int winner){myWinner =  winner;}
  public int getWinner(){return myWinner;}

  // Let's discuss, I think we should use the Java Streams class to create a Stream over the board declaratively, because:
// 1. We can use built in functionality for streams
// 2. Very easy to make code parallel/concurrent
// 3. Open-Closed -> we won't have to change implemenation if we decide to change how to represent Board because it will still be a Stream
  @Override
  public Iterator<Pair<Position, Piece>> iterator() {
    return new BoardIterator(pieceLocations);
  }


}
