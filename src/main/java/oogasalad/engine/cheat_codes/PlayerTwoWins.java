package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

public class PlayerTwoWins implements CheatCode{

  @Override
  public Board accept(Board board, Controller controller) {
    controller.getEngine().endGame(1);
    return new Board(board.getHeight(), board.getWidth());
  }
}
