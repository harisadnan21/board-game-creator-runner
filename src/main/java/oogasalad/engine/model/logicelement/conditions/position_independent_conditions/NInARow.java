package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.BoardUtilities;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;


public class NInARow extends BoardCondition{
  private int currentPlayer = -100;
  public NInARow(int[] parameters){
    super(parameters);
  }

  /**
   * evaluates if the condition is true
   *
   * @param board current board state
   * @param referencePoint
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return checkForHorizontal(board) || checkForVertical(board) || checkForDiagonal(board);
  }


  private boolean checkForHorizontal(Board board) {
    var rows = BoardUtilities.getRows(board).values().stream();
    return anyHaveNInARow(rows);
  }

  private boolean checkForVertical(Board board) {
    var cols = BoardUtilities.getCols(board).values().stream();
    return anyHaveNInARow(cols);

  }
  private boolean checkForDiagonal(Board board){
    for(PositionState state : board){
      if(nInARow(createLine(state.position(), board, -1)) || nInARow(createLine(state.position(), board, 1))){
        return true;
      }
    }
    return false;
  }

  private boolean anyHaveNInARow(Stream<List<PositionState>> positionStates) {
    return positionStates.anyMatch(this::nInARow);
  }

  private boolean nInARow(List<PositionState> positionStates) {

    int count = 0;
    for(PositionState positionState : positionStates) {
      count = positionState.player()==currentPlayer ? count+1 : (positionState.isEmpty() ? 0 : 1);
      currentPlayer = positionState.isEmpty() ? -100 : positionState.player();
      if (count == myParameters[0]) { return true; }
    }
    return false;
  }
  private List<PositionState> createLine(Position start, Board board, int direction){
    List<PositionState> line = new ArrayList<>();
    int row = start.row();
    int col = start.column();
    for(int i = 0; i<myParameters[0]; i++){
      if(board.isValidPosition(row, col)) {
        line.add(board.getPositionStateAt(row, col));
        row= row + direction;
        col= col + direction;
      }
    }
    return line;
  }


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
//
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
//
//}


}
