package oogasalad.engine.model.conditions;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public class WinCondition {
  private BoardCondition[] myEndConditions;
  private BoardCondition[] myWinDecision;

  public WinCondition(BoardCondition[] endConditions, BoardCondition[]winDecision){
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
