package oogasalad.engine.model.engine;

import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OutOfBoardException;

public class Place extends Action {

  public Place(int[] ints) {
    super(null);
  }

  @Override
  public void execute(Board board, int refI, int refJ) throws OutOfBoardException {

  }
}
