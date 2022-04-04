package oogasalad.engine.model.action;

import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

public class Remove extends Action {

  public Remove(int[] parameters) {
    super(parameters);
  }
  @Override
  public void execute(Board board, int refI, int refJ) throws OutOfBoardException {
    board.remove(myParameters[0]+refI, myParameters[1]+refJ);
  }
}
