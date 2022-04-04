package oogasalad.engine.model.action;

import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Board;

// TODO: implement class
public class ChangePieceType extends Action{


  public ChangePieceType(int[] parameters) {
    super(parameters);
  }

  @Override
  public void execute(Board board, int refI, int refJ) throws OutOfBoardException {

  }
}
