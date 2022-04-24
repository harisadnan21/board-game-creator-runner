package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

public class Player2Turn implements CheatCode{


  @Override
  public Board accept(Board board, Controller controller) {
    return board.setPlayer(1);
  }
}