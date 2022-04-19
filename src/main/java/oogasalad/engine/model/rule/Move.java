package oogasalad.engine.model.rule;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Defines Movements, which contain conditions and actions
 *
 * @author Jake Heller
 */
public class Move {

  private static final Logger LOG = LogManager.getLogger(Move.class);

  private Board myNextState;

  private String myName;
  private Condition[] myConditions;
  private Action[] myActions;
  private int myRepI;
  private int myRepJ;
  /**
   *
   * @param conditions
   * @param actions
   * @param repI
   * @param repJ
   */
  public Move(String name, Condition[] conditions, Action[] actions, int repI, int repJ) {
    myName = name;
    myConditions = conditions;
    myActions = actions;
    myRepI = repI;
    myRepJ = repJ;
  }

  private boolean isValid(Board board, int refI, int refJ) {
    try {
      for (Condition condition : myConditions) {
        if (!condition.isTrue(board, new Position(refI, refJ))) {
          return false;
        }
      }
      return true;
    } catch (OutOfBoardException e) {
      return false;
    }
  }

  public boolean isValid(Board board, Position referencePoint) {
    try {
      for (Condition condition : myConditions) {
        if (!condition.isTrue(board, referencePoint)) {
          return false;
        }
      }
      return true;
    } catch (OutOfBoardException e) {
      return false;
    }
  }

  /**
   * Returns the name given to this rule
   * @return
   */
  public String getName() {
    return myName;
  }

  private Position getRepresentativeCell(int i, int j) {
    return new Position(myRepI + i, myRepJ + j);
  }

  /**
   *
   * @param referencePoint reference point for move
   * @return
   */
  public Position getRepresentativeCell(Position referencePoint) {
    return getRepresentativeCell(referencePoint.i(), referencePoint.j());
  }

  private Board doMovement(Board board, int refI, int refJ) {
    if (isValid(board, refI, refJ)) {

      LOG.info("{} has {} conditions and {} actions", myName, myConditions.length, myActions.length);

      for (Action action: myActions) {
        board = action.execute(board, refI, refJ);
      }
      board = board.setPlayer((board.getPlayer() + 1) % 2); //Make less magical
      return board;
    }
    return null;
  }

  public Board doMovement(Board board, Position referencePoint) {
    return doMovement(board, referencePoint.i(), referencePoint.j());
  }
}
