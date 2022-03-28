package oogasalad.engine.model;

@FunctionalInterface
public interface AvailableMovesForPiece {

    public Boolean AvailableMovesForPiece(Board board, Piece piece, Player player, Rule[] rules);

}
