package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.action.ActionType;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface LegalMove {

public Boolean legalMove(Board board, ActionType action, Player currentPlayer, Rule[] rules);

}
