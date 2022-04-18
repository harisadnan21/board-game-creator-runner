package oogasalad.engine.model.ai;

import java.util.Set;
import oogasalad.engine.model.board.Board;

public interface AIOracle {
  Set<AIChoice> getChoices(Board board, int player);

  Boolean isWinningState(Board board);

}
