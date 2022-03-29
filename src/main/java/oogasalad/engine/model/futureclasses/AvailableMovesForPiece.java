package oogasalad.engine.model.futureclasses;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.Player;
import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface AvailableMovesForPiece {

    public Boolean AvailableMovesForPiece(Board board, Piece piece, Player player, Rule[] rules);

}
