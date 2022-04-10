package oogasalad.engine.model.board;

import java.util.Iterator;

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
  public PositionState next() {
    if (currColumn + 1 == myColumns) {
      currRow++;
      currColumn = 0;
    }
    else {
      currColumn++;
    }
    Piece piece = myPieces[currRow][currColumn];
    if (piece == null) {
      piece = Piece.EMPTY;
    }
    Position position = new Position(currRow, currColumn);
    return new PositionState(position, piece);
  }
}
