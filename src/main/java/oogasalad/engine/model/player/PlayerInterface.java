package oogasalad.engine.model.player;

import oogasalad.engine.model.engine.Choice;

/**
 * 
 */
public interface PlayerInterface {

    /**
     * @return
     */
     Choice chooseMove();

  void onCellSelect(int i, int j);
}