package oogasalad.engine.model.rule;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.logicelement.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Defines Movements, which contain conditions and actions
 *
 * @author Jake Heller
 */
public class Move implements Rule {

  private static final Logger LOG = LogManager.getLogger(Move.class);

  private String myName;
  private Condition[] myConditions;
  private Action[] myActions;
  private int myRepI;
  private int myRepJ;
  private Position myRepresentativePoint;
  private boolean myIsPersistent;

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

  /**
   *
   * @param name name of rule
   * @param conditions conditions which all must be true for rule to be valid
   * @param actions actions which get executed when rule is chosen
   * @param representativePoint relative point which might be shown to a user to represent this move
   */
  public Move(String name, Condition[] conditions, Action[] actions, Position representativePoint) {
    this(name, conditions, actions, representativePoint, false);
  }

  /**
   *
   * @param name name of rule
   * @param conditions conditions which all must be true for rule to be valid
   * @param actions actions which get executed when rule is chosen
   * @param representativePoint relative point which might be shown to a user to represent this move
   * @param isPersistent if the rule is persistent (executed after every move)
   */
  public Move(String name, Condition[] conditions, Action[] actions, Position representativePoint, boolean isPersistent) {
    myName = name;
    myConditions = conditions;
    myActions = actions;
    myRepI = representativePoint.row();
    myRepJ = representativePoint.column();
    myRepresentativePoint = representativePoint;
    myIsPersistent = isPersistent;
  }

  /**
   * Checks to see if rule is valid
   * @param board
   * @param referencePoint
   * @return
   */
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
   * Returns true if this move is persistent
   * @return
   */
  public boolean isPersistent() {
    return myIsPersistent;
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
    return getRepresentativeCell(referencePoint.row(), referencePoint.column());
  }

  /**
   *
   * @param board resultant board
   * @param referencePoint
   * @return
   */
  public Board doMove(Board board, Position referencePoint) {
    if (isValid(board, referencePoint)) {

      LOG.info("{} has {} conditions and {} actions", myName, myConditions.length, myActions.length);

      for (Action action: myActions) {
        board = action.execute(board, referencePoint);
      }
      board = board.setPlayer((board.getPlayer() + 1) % 2); //Make less magical
      return board;
    }
    return board;
  }
}
