package oogasalad.engine.model.conditionals;

import oogasalad.builder.model.element.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;

@FunctionalInterface
public interface Rules {
  public Boolean Rule(Board board, Action action, Player currentPlayer);
}
