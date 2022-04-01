package oogasalad.engine.model.conditionals;

import oogasalad.engine.model.misc.Player;
import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface Rules {
  public Boolean Rule(Board board, Action action, Player currentPlayer);
}
