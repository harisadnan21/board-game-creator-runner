package oogasalad.engine.model.futureclasses;

import oogasalad.engine.model.Action;
import oogasalad.engine.model.Board;
import oogasalad.engine.model.Player;
import oogasalad.engine.model.Rule;

@FunctionalInterface
public interface LegalMove {

public Boolean legalMove(Board board, Action action, Player currentPlayer, Rule[] rules);

}
