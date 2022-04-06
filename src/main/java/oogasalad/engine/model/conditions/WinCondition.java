package oogasalad.engine.model.conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.board_conditions.BoardCondition;

public class WinCondition {
  private BoardCondition[] myEndConditions;
  private Winner[] myWinDecision;

  public WinCondition(BoardCondition[] endConditions, Winner[]winDecision){
    myEndConditions = endConditions;
    myWinDecision = winDecision;
  }

  public boolean isOver(Board board) throws OutOfBoardException {
    for(BoardCondition endCondition : myEndConditions){
      if(!endCondition.isTrue(board)){
        return false;
      }
    }
    return true;
  }

}
