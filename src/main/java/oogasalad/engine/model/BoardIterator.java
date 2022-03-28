package oogasalad.engine.model;

import java.util.Iterator;

public class BoardIterator<Piece> implements Iterator<Piece> {

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
  public Piece next() {
    if (currColumn + 1 == myColumns) {
      currRow++;
      currColumn = 0;
    }
    else {
      currColumn++;
    }
    return myPieces[currRow][currColumn];
  }
}
