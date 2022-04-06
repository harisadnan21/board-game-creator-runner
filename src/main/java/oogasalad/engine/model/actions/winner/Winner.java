package oogasalad.engine.model.actions.winner;

import oogasalad.engine.model.board.Board;

public interface Winner {
  int decideWinner(Board board);


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
