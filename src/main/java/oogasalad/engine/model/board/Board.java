package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import javafx.util.Pair;
import oogasalad.engine.model.Observable;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.Utilities;

public class Board extends Observable<Piece[][]> implements Iterable<Pair<Position, Piece>> {

  private int myRows;
  private int myColumns;
  private Piece[][] pieceLocations;

  public Board(int rows, int columns) {
    myRows = rows;
    myColumns = columns;
    pieceLocations = new Piece[rows][columns];
  }

  public Piece[][] getMyBoard(){
    return pieceLocations;
  }

  public void selectCell(int x, int y){
    Piece[][] oldBoard = pieceLocations;
    if (pieceLocations[x][y] != null) {
      pieceLocations[x][y] = null;
    }
    else {
      place(x, y, new Piece("...", 1));
    }
    notifyListeners("UPDATE", oldBoard, pieceLocations);
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
  public void placeNewPiece(int row, int column, Piece piece){
    place(row, column, piece);
  }
  private void place(int i, int j, Piece piece){
    pieceLocations[i][j] = piece;
  }

  public void remove(int i, int j){pieceLocations[i][j] =null;};

  public Piece getPiece(int i, int j) {
    //return Optional.of(myBoard[i][j]);
    return pieceLocations[i][j];
  }


  /**
   *
   * @param i end i position
   * @param j end j position
   * @param piece
   */
  public void move(int i, int j, Piece piece) {
    if (!isPieceAtLocation(i,j)){
      place(i, j, piece);
      remove(i, j);
    }
  }

  public Boolean isValid(Position position){
    return isValidX(position.getI()) && isValidY(position.getJ());
  }

  private boolean isValidY(int j) {
    return Utilities.isPositive(j) && (j <= myRows);
  }

  private boolean isValidX(int i) {
    return Utilities.isPositive(i) && (i <= myColumns);
  }

  public Board deepCopy() {
    Board board = new Board(myRows, myColumns);
    for (Pair<Position, Piece> piece: this) {
      Piece copyPiece;
      if (piece.getValue() == null) {
        copyPiece = null;
      }
      else {
        copyPiece = piece.getValue().deepCopy();
      }
      board.place(piece.getKey().getI(), piece.getKey().getJ(), copyPiece);
    }
    return board;
  }

  // Let's discuss, I think we should use the Java Streams class to create a Stream over the board declaratively, because:
// 1. We can use built in functionality for streams
// 2. Very easy to make code parallel/concurrent
// 3. Open-Closed -> we won't have to change implemenation if we decide to change how to represent Board because it will still be a Stream
  @Override
  public Iterator<Pair<Position, Piece>> iterator() {
    return new BoardIterator(pieceLocations);
  }
}
