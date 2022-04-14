package oogasalad.engine.model.conditions.board_conditions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;


public class NInARow extends BoardCondition{
  private int currentPlayer = -1;
  private int n;
  public NInARow(int[] parameters){
    super(parameters);
  }

  /**
   * evaluates if the condition is true
   *
   * @param board current board state
   */
  @Override
  public boolean isTrue(Board board) {
    return checkForHorizontal(board) || checkForVertical(board);


  }

  //check for n pieces of the same type in a horizontal row
  private boolean checkForHorizontal(Board board) {
    n = 0;
    for(int row = 0; row < board.getHeight(); row++){
      for(int col = 0; col < board.getWidth(); col++){
        if (checkCurrentCell(board, row, col)) {
          return true;
        }
      }
      n = 0;
    }
    return false;
  }

  //check for n pieces of the same type in a vertical row
  private boolean checkForVertical(Board board) {
    n = 0;
    for(int col = 0; col < board.getWidth(); col++){
      for(int row = 0; row < board.getHeight(); row++){
        if (checkCurrentCell(board, row, col)) {
          return true;
        }
      }
      n = 0;
    }
    return false;
  }

  private boolean checkCurrentCell(Board board, int row, int col) {
    if(board.getPositionStateAt(row, col).piece() != Piece.EMPTY) {
      if (board.getPositionStateAt(row, col).player() == currentPlayer) {
        n++;
      } else {
        currentPlayer = board.getPositionStateAt(row, col).player();
        n = 1;
      }
    }
    else{
      n = 0;
    }
    return n == myParameters[0];
  }

}
