package oogasalad.engine.model.futureclasses;

import oogasalad.engine.model.Action;
import oogasalad.engine.model.Board;
import oogasalad.engine.model.Player;
import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface AvailableActions {

    public Action[] availableActions(Board board, Player player, Rule[] rules);

}
// available actions -> a function/mapping between a state of the board, a player, and a list of rules that returns a list of actions
