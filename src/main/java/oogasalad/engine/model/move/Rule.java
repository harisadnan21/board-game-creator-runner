package oogasalad.engine.model.move;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;

/**
 * Defines Movements, which contain conditions and actions
 *
 * @author Jake Heller
 */
public class Rule {

  private Board myNextState;

  private PieceCondition[] myConditions;
  private Action[] myActions;
  private int myRepI; // i value for the "representative cell" for this action
  private int myRepJ; // j value for the "representative cell" for this action

  /**
   *
   * @param conditions
   * @param actions
   * @param repI
   * @param repJ
   */
  public Rule(PieceCondition[] conditions, Action[] actions, int repI, int repJ) {
    myConditions = conditions;
    myActions = actions;
    myRepI = repI;
    myRepJ = repJ;
  }

  public boolean isValid(Board board, int refI, int refJ) {
    try {
      for (PieceCondition condition : myConditions) {
        if (!condition.isTrue(board, refI, refJ)) {
          return false;
        }
      }
      return true;
    } catch (OutOfBoardException e) {
      return false;
    }
  }

  /**
   *
   * @param i location of selected piece
   * @param j location of selected piece
   * @return
   */
  public Position getRepresentativeCell(int i, int j) {
    return new Position(myRepI + i, myRepJ + j);
  }

  public Board doMovement(Board board, int refI, int refJ)
      throws OutOfBoardException {
    if (isValid(board, refI, refJ)) {

      Board boardCopy = board;

      for (Action action: myActions) {
//        boardCopy = action.execute(boardCopy, refI, refJ);
        boardCopy = action.execute(board, refI, refJ);
      }
      boardCopy.setPlayer((board.getPlayer() + 1) % 2); //Make less magical
      return boardCopy;
    }
    return null;
  }
}
