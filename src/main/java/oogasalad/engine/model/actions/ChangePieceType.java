package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.misc.OutOfBoardException;
import oogasalad.engine.model.board.boards.Board;

// TODO: implement class
public class ChangePieceType extends Action{


  public ChangePieceType(int[] parameters) {
    super(parameters);
  }

  @Override
  public Board execute(Board board, int refI, int refJ) throws OutOfBoardException {
    return null;
  }
}
