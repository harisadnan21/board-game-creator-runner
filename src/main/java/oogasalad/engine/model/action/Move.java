package oogasalad.engine.model.action;

import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.misc.Piece;
import oogasalad.engine.model.board.Board;

public class Move implements Action {

  public Move(Board board, int i, int j, Piece piece) {

  }

  @Override
  public void execute(Game game) {

  }

  @Override
  public Board getNextState() {
    return null;
  }
}
