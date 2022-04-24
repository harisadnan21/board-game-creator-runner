package oogasalad.engine.cheat_codes;

import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;

public interface CheatCode {

  public Board accept(Board board, Controller controller);
}
