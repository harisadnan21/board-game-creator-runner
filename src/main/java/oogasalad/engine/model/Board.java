package oogasalad.engine.model;

import java.util.Iterator;
import javafx.util.Pair;

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
      place(x, y);
    }
    notifyListeners("UPDATE", oldBoard, myBoard);
  }

  private void place(int x, int y){
    myBoard[x][y] = new Piece("Knight", 1);
  }

  public Boolean isValid(Position position){
    return isValidX(position.getI()) && isValidY(position.getJ());
  }

  private boolean isValidY(int j) {
    return Utilities.isPositive(j) && (j <= myRows);
  }

  private Boolean isValidX(int i) {
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
