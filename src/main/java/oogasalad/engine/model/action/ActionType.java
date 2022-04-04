package oogasalad.engine.model.action;

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