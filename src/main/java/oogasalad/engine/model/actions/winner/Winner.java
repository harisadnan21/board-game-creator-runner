package oogasalad.engine.model.actions.winner;

import oogasalad.engine.model.board.Board;

/**
 * Interface for the different ways to decide how a game has been won
 * @author Robert Cranston
 */
public interface Winner {

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
    int maxIndex = 0;
    int maxValue = 0;
    for(int i = 0; i < array.length; i++){
      if(array[i]>maxValue){
        maxValue = array[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }
}
