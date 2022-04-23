package oogasalad.engine.cheat_codes;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.ImmutableBoard;

public class RemoveSelectedPiece implements CheatCode{

  @Override
  public ImmutableBoard accept(Board board) {
    //board.remove();
    return board;
  }



}
