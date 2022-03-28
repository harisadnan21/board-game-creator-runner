package oogasalad.engine.model;

@FunctionalInterface
public interface BoardTrait {

    public Boolean AvailableMovesForPiece(Board board);

}
