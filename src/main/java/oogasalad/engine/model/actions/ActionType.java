package oogasalad.engine.model.actions;

import oogasalad.engine.model.board.OldPiece;

/**
 * 
 */
@FunctionalInterface
public interface ActionType {

    /**
     * 
     */
    public void execute(int i, int j, OldPiece piece);
}