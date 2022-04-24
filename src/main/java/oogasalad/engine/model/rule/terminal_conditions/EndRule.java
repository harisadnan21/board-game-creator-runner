package oogasalad.engine.model.rule.terminal_conditions;
import oogasalad.engine.model.logicelement.winner.WinDecision;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.logicelement.conditions.Condition;
import oogasalad.engine.model.rule.Rule;

/**
 * Class to hold one specific win condition. A win condition consists of a set of conditions that
 * signal that the game is over and a decision for how to decide which player wins the game
 *
 * @author Robert Cranston, Haris Adnan
 * @see WinDecision
 * @see Condition
 */
public class EndRule implements Rule {
  private final  Condition[] myEndConditions;
  private final WinDecision myWinDecision;
  private String myName;

  /**
   * Sets end and winner conditions
   * @param endConditions set of conditions that signal the game is over for this specific win condition
   * @param winDecision Winner decision on how to determine a winner
   */
  public EndRule(String name, Condition[] endConditions, WinDecision winDecision) {
    myWinDecision = winDecision;
    myEndConditions = endConditions;
    myName = name;
  }

  /**
   * Returns true if all the end game conditions are met and false otherwise
   * @param board current game board
   * @param representativePoint
   * @return true if all end conditions met, false if not
   */
  @Override
  public boolean isValid(Board board, Position representativePoint) {
    for(Condition endCondition : myEndConditions){
      // in the end conditions, you can use all the piece conditions with absolute parameters
      // if 0,0 is always the reference point
      if(!endCondition.isTrue(board, representativePoint)){
        return false;
      }
    }
    return true;
  }

  @Override
  public String getName() {
    return myName;
  }


  /**
   * Decides winner for current board state based on the win decision declared in the constructor.
   * @param board current board state
   * @return int representing the winning player
   */

  public int getWinner(Board board){
    return myWinDecision.decideWinner(board);
  }

}
