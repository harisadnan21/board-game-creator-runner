package oogasalad.engine.cheat_codes;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Engine;

public class PlayerTwoWins implements CheatCode{

  @Override
  public Board accept(Board board, Engine engine) {
    engine.endGame(1);
    return new Board(board.getHeight(), board.getWidth());
  }
}
