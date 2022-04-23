package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.board.exceptions.OutOfBoardException;
import oogasalad.engine.model.logicelement.conditions.Condition;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.rule.Move;

/**
 * Class that evaluates if the game is at a standstill when no player has any available moves left
 * to perform.(leading the game to end in a draw)
 * @author Haris Adnan
 */
public class NoMovesLeft extends BoardCondition{
  int[] myConditions;
  public NoMovesLeft(int[] parameters){
    super(parameters);
    myConditions = parameters;
  }
  /**
   *
   * @param board current board state
   * @param referencePoint
   * @return Returns True if no moves left, else returns false
   */
  @Override
  public boolean isTrue(Board board, Position referencePoint) {

    for(PositionState state : board){
      if(!state.isEmpty()){
        int player = state.piece().player();
      }
    }
    return false;
  }

  private boolean isValid(Board board, Position referencePoint) {
    try {
      for (int condition : myConditions) {
        if (condition != 0) {
          return false;
        }
      }
      return true;
    } catch (OutOfBoardException e) {
      return false;
    }
  }
}
