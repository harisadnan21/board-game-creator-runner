package oogasalad.engine.model.conditions.board_conditions;

import java.util.List;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.PositionState;


public class NInARow extends BoardCondition{
  private int currentPlayer = -100;
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

//  //check for n pieces of the same type in a horizontal row
//  private boolean checkForHorizontal(Board board) {
//    n = 0;
//    for(int row = 0; row < board.getHeight(); row++){
//      for(int col = 0; col < board.getWidth(); col++){
//        if (checkCurrentCell(board, row, col)) {
//          return true;
//        }
//      }
//      n = 0;
//    }
//    return false;
//  }

  //check for n pieces of the same type in a horizontal row
  private boolean checkForHorizontal(Board board) {
    var posStates = board.getRows().values().stream();
    return anyHaveNInARow(posStates);
  }

  private boolean checkForVertical(Board board) {
    var posStates = board.getCols().values().stream();
    return anyHaveNInARow(posStates);

  }

  private boolean anyHaveNInARow(Stream<List<PositionState>> positionStates) {
    return positionStates.anyMatch(posStates -> nInARow(posStates));
  }

  private boolean nInARow(List<PositionState> positionStates) {
//    return positionStates.mapToInt(PositionState::player).distinct().count() == 1;
    int count = 0;
    for(PositionState positionState : positionStates) {
      count = positionState.player()==currentPlayer ? count+1 : 0;
      currentPlayer = positionState.isEmpty() ? -100 : positionState.player();
      if (count == n) { return true; }
    }
    return false;
  }

//  //check for n pieces of the same type in a vertical row
//  private boolean checkForVertical(Board board) {
//    n = 0;
//    for(int col = 0; col < board.getWidth(); col++){
//      for(int row = 0; row < board.getHeight(); row++){
//        if (checkCurrentCell(board, row, col)) {
//          return true;
//        }
//      }
//      n = 0;
//    }
//    return false;
//  }
//
//  private boolean checkCurrentCell(Board board, int row, int col) {
//    if(board.getPositionStateAt(row, col).piece() != Piece.EMPTY) {
//      if (board.getPositionStateAt(row, col).player() == currentPlayer) {
//        n++;
//      } else {
//        currentPlayer = board.getPositionStateAt(row, col).player();
//        n = 1;
//      }
//    }
//    else{
//      n = 0;
//    }
//    return n == myParameters[0];
//  }

}
