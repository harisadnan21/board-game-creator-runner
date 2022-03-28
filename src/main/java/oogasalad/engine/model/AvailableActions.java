package oogasalad.engine.model;

import oogasalad.engine.model.Action;

@FunctionalInterface
public interface AvailableActions {

    public Action[] availableActions(Board board, Player player, Rule[] rules);

}
// available actions -> a function/mapping between a state of the board, a player, and a list of rules that returns a list of actions
