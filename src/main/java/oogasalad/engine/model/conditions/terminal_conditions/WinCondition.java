package oogasalad.engine.model.conditions.terminal_conditions;
import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.conditions.board_conditions.BoardCondition;

/**
 * Class to hold one specific win condition. A win condition consists of a set of conditions that
 * signal that the game is over and a decision for how to decide which player wins the game
 *
 * @author Robert Cranston
 * @see Winner
 * @see BoardCondition
 */
public class WinCondition{
  private final  BoardCondition[] myEndConditions;
  private final Winner myWinDecision;

  /**
   * Sets end and winner conditions
   * @param endConditions set of conditions that signal the game is over for this specific win condition
   * @param winDecision Winner decision on how to determine a winner
   */
  public WinCondition(BoardCondition[] endConditions, Winner winDecision){
    myWinDecision = winDecision;
    myEndConditions = endConditions;
  }
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


  /**
   * Decides winner for current board state based on the win decision declared in the constructor.
   * @param board current board state
   * @return int representing the winning player
   */

  public int getWinner(Board board){
    return myWinDecision.decideWinner(board);
  }

}
