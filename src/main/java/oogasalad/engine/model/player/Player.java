package oogasalad.engine.model.player;

import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.action.ActionType;

/**
 * 
 */
public interface Player {

    /**
     * 
     */
    public void playTurn();

    /**
     * @param PossibleActions
     */
    public void chooseAction(Action[] PossibleActions);

}