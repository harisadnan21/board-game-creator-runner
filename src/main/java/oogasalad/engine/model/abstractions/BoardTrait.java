package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.board.ArrayBoard;

@FunctionalInterface
public interface BoardTrait {

    public Boolean AvailableMovesForPiece(ArrayBoard board);

}
