package oogasalad.engine.model.player;

import oogasalad.engine.model.action.Action;

/**
 * 
 */
public interface PlayerInterface {

    /**
     * 
     */
    public void playTurn();

    /**
     * @param PossibleActions
     */
    public void chooseAction(Action[] PossibleActions);

}