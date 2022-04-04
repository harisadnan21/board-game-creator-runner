package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.player.Player;

import oogasalad.engine.model.player.PlayerInterface;
//import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface AvailableActions {

    public Action[] availableActions(Board board, Player player, Rule[] rules);

}
// available actions -> a function/mapping between a state of the board, a player, and a list of rules that returns a list of actions
