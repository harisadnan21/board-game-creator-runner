package oogasalad.engine.model.action;

import oogasalad.engine.model.Piece;

/**
 * 
 */
@FunctionalInterface
public interface ActionType {

    /**
     * 
     */
    public void execute(int i, int j, Piece piece);
}