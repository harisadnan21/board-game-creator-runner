package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.Piece;

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