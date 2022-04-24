package oogasalad.engine.cheat_codes;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Engine;

public interface CheatCode {

  public Board accept(Board board, Engine engine);
}
