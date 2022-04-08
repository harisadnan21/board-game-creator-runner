package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.actions.ActionType;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.player.Player;

@FunctionalInterface
public interface LegalMove {

public Boolean legalMove(ArrayBoard board, ActionType action, Player currentPlayer, Rule[] rules);

}
