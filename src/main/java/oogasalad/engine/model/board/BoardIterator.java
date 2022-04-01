package oogasalad.engine.model.board;

import java.util.Iterator;
import javafx.util.Pair;
import oogasalad.engine.model.misc.Piece;

public class BoardIterator implements Iterator {


  private Piece[][] myPieces;
  private int myRows;
  private int myColumns;

  private int currRow;
  private int currColumn;

  public BoardIterator(Piece[][] pieces) {
    myPieces = pieces;
    myRows = pieces.length;
    myColumns = pieces[0].length;
  }

  @Override
  public boolean hasNext() {
    if ((currColumn + 1 == myColumns) && currRow + 1 == myRows ) {
      return false;
    }
    return true;
  }

  @Override
  public Pair<Position, Piece> next() {
    if (currColumn + 1 == myColumns) {
      currRow++;
      currColumn = 0;
    }
    else {
      currColumn++;
    }
    Piece piece = myPieces[currRow][currColumn];
    Position position = new Position(currRow, currColumn);
    return new Pair<>(position, piece);
  }
}
