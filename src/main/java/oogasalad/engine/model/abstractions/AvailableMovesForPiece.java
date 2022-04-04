package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface AvailableMovesForPiece {

    public Boolean AvailableMovesForPiece(Board board, Piece piece, Player player, Rule[] rules);

}
