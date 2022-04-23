package oogasalad.engine.cheat_codes;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.ImmutableBoard;

public interface CheatCode {

  public ImmutableBoard accept(Board board);
}
