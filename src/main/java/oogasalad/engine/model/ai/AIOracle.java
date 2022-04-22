package oogasalad.engine.model.ai;

import java.util.Set;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;

public interface AIOracle {
  Set<Choice> getChoices(Board board, int player);

  Boolean isWinningState(Board board);

}
