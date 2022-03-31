package oogasalad.engine.model.board;

import java.util.Iterator;
import javafx.util.Pair;
import oogasalad.engine.model.Observable;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.Utilities;

public class Board extends Observable<Piece[][]> implements Iterable<Pair<Position, Piece>> {

  private int myRows;
  private int myColumns;
  private Piece[][] myBoard;

  public Board(int rows, int columns) {
    myRows = rows;
    myColumns = columns;
    myBoard = new Piece[rows][columns];
  }

  public void selectCell(int x, int y){
    Piece[][] oldBoard = myBoard;
    if (myBoard[x][y] != null) {
      myBoard[x][y] = null;
    }
    else {
      place(x, y, new Piece("Knight", 1));
    }
    notifyListeners("UPDATE", oldBoard, myBoard);
  }


  /**
   * returns true if there is a piece at location Board[row][column]. else, false
   * @param row
   * @param column
   * @return
   */
  private boolean isPieceAtLocation(int row, int column){
    return myBoard[row][column] != null;

  }
  public void placeNewPiece(int row, int column, Piece piece){
    place(row, column, piece);
  }
  private void place(int i, int j, Piece piece){
    myBoard[i][j] = piece;
  }
  private void remove(int i, int j){myBoard[i][j] =null;};

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

  // Let's discuss, I think we should use the Java Streams class to create a Stream over the board declaratively, because:
// 1. We can use built in functionality for streams
// 2. Very easy to make code parallel/concurrent
// 3. Open-Closed -> we won't have to change implemenation if we decide to change how to represent Board because it will still be a Stream
  @Override
  public Iterator<Pair<Position, Piece>> iterator() {
    return new BoardIterator(myBoard);
  }
}
