package oogasalad.engine.model.move;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.Condition;

public class Movement {

  private Board myNextState;

  private Condition[] myConditions;
  private Action[] myActions;

  public Movement(Condition[] conditions, Action[] actions) {
    myConditions = conditions;
    myActions = actions;
  }

  public boolean isValid(Board board, int refI, int refJ) {
    try {
      for (Condition condition : myConditions) {
        if (!condition.isTrue(board, refI, refJ)) {
          return false;
        }
      }
      return true;
    } catch (OutOfBoardException e) {
      return false;
    }
  }

  public Board doMovement(Board board, int refI, int refJ)
      throws OutOfBoardException {
    if (isValid(board, refI, refJ)) {
      Board boardCopy = board.deepCopy();
      for (Action action: myActions) {
        action.execute(boardCopy, refI, refJ);
      }
      return boardCopy;
    }
    return null;
  }
}
