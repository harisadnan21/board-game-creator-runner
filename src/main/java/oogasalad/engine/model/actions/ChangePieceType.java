package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;

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
