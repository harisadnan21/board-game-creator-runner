package oogasalad.engine.model;

import java.util.Iterator;
import java.util.List;
import javafx.beans.InvalidationListener;

public class Board extends Observable<Piece[][]> implements Iterable<Piece> {

  private int myHeight;
  private int myWidth;
  private Piece[][] myBoard;

  public Board(int width, int height) {
    myHeight = height;
    myWidth = width;
    myBoard = new Piece[height][width];
  }

  public void selectCell(int x, int y){
    Piece[][] oldBoard = myBoard;
    place(x, y);
    notifyListeners("UPDATE", oldBoard, myBoard);
  }

  private void place(int x, int y){
    myBoard[x][y] = new Piece("Knight", 1);
  }

  public Boolean isValid(Position position){
    return isValidX(position.getI()) && isValidY(position.getJ());
  }

  private boolean isValidY(int j) {
    return Utilities.isPositive(j) && (j <= myHeight);
  }

  private Boolean isValidX(int i) {
    return Utilities.isPositive(i) && (i <= myWidth);
  }

  // Let's discuss, I think we should use the Java Streams class to create a Stream over the board declaratively, because:
// 1. We can use built in functionality for streams
// 2. Very easy to make code parallel/concurrent
// 3. Open-Closed -> we won't have to change implemenation if we decide to change how to represent Board because it will still be a Stream
  @Override
  public Iterator<Piece> iterator() {
    return new BoardIterator(myBoard);
  }
}
