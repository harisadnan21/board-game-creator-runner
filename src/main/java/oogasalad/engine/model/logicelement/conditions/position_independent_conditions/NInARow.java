package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.List;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.BoardUtilities;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;


public class NInARow extends BoardCondition{
  private int currentPlayer = -100;
  private int n;

  /**
   * Returns true if there exists n in a row in any direction
   * @param parameters [n]
   */
  public NInARow(int[] parameters){
    super(parameters);
    n = myParameters[0];
  }

  /**
   * evaluates if the condition is true
   *
   * @param board current board state
   * @param referencePoint
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    return checkForHorizontal(board) || checkForVertical(board);
  }


  private boolean checkForHorizontal(Board board) {
    var rows = BoardUtilities.getRows(board).values().stream();
    return anyHaveNInARow(rows);
  }

  private boolean checkForVertical(Board board) {
    var cols = BoardUtilities.getCols(board).values().stream();
    return anyHaveNInARow(cols);

  }

  private boolean anyHaveNInARow(Stream<List<PositionState>> positionStates) {
    return positionStates.anyMatch(this::nInARow);
  }

  private boolean nInARow(List<PositionState> positionStates) {

    int count = 0;
    for(PositionState positionState : positionStates) {
      count = positionState.player()==currentPlayer ? count+1 : 0;
      currentPlayer = positionState.isEmpty() ? -100 : positionState.player();
      if (count == n) { return true; }
    }
    return false;
  }

}
