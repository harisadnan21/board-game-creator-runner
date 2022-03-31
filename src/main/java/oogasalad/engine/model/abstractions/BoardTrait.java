package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface BoardTrait {

    public Boolean AvailableMovesForPiece(Board board);

}
