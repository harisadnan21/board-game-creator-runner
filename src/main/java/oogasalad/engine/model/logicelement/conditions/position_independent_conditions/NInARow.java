package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.utilities.BoardUtilities;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;


public class NInARow extends BoardCondition{
  private int currentPlayer = -100;

  private int n;

  /**
   * Returns true if there exists n in a row in any direction
   * @param parameters [n]
   */
  public NInARow(int[] parameters){
    super(parameters);
    n = getParameter(0);
  }

  /**
   * evaluates if the condition is true
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
      count = positionState.player()== currentPlayer ? count+1 : (positionState.isEmpty() ? 0 : 1);
      currentPlayer = positionState.isEmpty() ? -100 : positionState.player();
      if (count == n) { return true; }
    }
    return false;
  }
  private List<PositionState> createLine(Position start, Board board, int direction){
    List<PositionState> line = new ArrayList<>();
    int row = start.row();
    int col = start.column();
    for(int i = 0; i< n; i++){
      if(board.isValidPosition(row, col)) {
        line.add(board.getPositionStateAt(row, col));
        row= row + direction;
        col= col + direction;
      }
    }
    return line;
  }


}
