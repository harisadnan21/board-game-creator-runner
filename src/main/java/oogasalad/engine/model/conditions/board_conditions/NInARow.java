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
