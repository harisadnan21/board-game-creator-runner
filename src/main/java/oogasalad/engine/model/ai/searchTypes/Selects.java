package oogasalad.engine.model.ai.searchTypes;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface Selects {

  AIChoice selectChoice(Board board);
}
