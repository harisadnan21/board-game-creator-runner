package oogasalad.engine.model.abstractions;

import oogasalad.builder.model.element.Rule;
import oogasalad.engine.model.actions.ActionType;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;

@FunctionalInterface
public interface LegalMove {

public Boolean legalMove(Board board, ActionType action, Player currentPlayer, Rule[] rules);

}
