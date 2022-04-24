package oogasalad.engine.model.logicelement.winner;

import static org.jooq.lambda.Seq.range;

import oogasalad.engine.model.board.Board;
import org.jooq.lambda.Seq;

/**
 * Interface for the different ways to decide how a game has been won
 * @author Alex Bildner, Robert Cranston
 */
public interface WinDecision {

  /**
   * Function decides who the winner of the game is depending on what the game is and what the state
   * of the board is
   * @param board
   * @return : int 0 or 1, depending on who won
   */
  int decideWinner(Board board);

  /**
   * Used to determine the index of the max element in an array. Used multiple times in deciding a winner.
   * @param array int array to containing one or more elements.
   * @return index of max element in array.
   */
  static int maxValueIndex(int[] array){
    return range(0, array.length).maxBy(index -> array[index]).get();
  }
}
