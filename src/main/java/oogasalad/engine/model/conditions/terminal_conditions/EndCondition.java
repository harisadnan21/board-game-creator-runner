package oogasalad.engine.model.conditions.terminal_conditions;

import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.conditions.board_conditions.BoardCondition;

/**
 * Ahstract class that determines when end condtions - draw and or one player wins - are acheived.
 * @author Haris Adnan
 */
public abstract class EndCondition {
  private BoardCondition[] myEndConditions;
  private Winner myWinDecision;

  /**
   * Returns true if all the end game conditions are met and false otherwise
   * @param board current game board
   * @return whether end conditions have been met
   */
  public boolean isOver(Board board) {
    for(BoardCondition endCondition : myEndConditions){
      if(!endCondition.isTrue(board)){
        return false;
      }
    }
    return true;
  }

}
