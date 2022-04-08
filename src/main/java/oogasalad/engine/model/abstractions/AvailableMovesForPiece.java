package oogasalad.engine.model.abstractions;

import oogasalad.builder.model.element.Rule;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.OldPiece;
import oogasalad.engine.model.player.Player;
//import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface AvailableMovesForPiece {

    public Boolean AvailableMovesForPiece(Board board, OldPiece piece, Player player, Rule[] rules);

}
