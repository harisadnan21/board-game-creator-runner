package oogasalad.engine.model.futureclasses;

import oogasalad.engine.model.Board;

@FunctionalInterface
public interface BoardTrait {

    public Boolean AvailableMovesForPiece(Board board);

}
