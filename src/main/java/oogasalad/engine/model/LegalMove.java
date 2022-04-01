package oogasalad.engine.model;

import java.util.Arrays;
import oogasalad.engine.model.action.ActionType;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.Player;
import oogasalad.engine.model.Rules;

public class LegalMove {

public Boolean legalMove(Board board, ActionType action, Player currentPlayer, Rules[] rules){
  Arrays.stream(rules).map(rule()
}

}
