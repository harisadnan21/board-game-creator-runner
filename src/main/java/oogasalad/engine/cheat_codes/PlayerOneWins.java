package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Engine;

public class PlayerOneWins implements CheatCode{

  @Override
  public Board accept(Board board, Controller controller) {
    controller.getEngine().endGame(0);
    return new Board(board.getHeight(), board.getWidth());
  }
}
