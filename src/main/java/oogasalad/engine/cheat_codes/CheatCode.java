package oogasalad.engine.cheat_codes;

import oogasalad.engine.model.board.Board;

public abstract class CheatCode {

  public Board accept(Board board){
    return board;
  };
}
