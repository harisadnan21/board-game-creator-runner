package oogasalad.engine.model.ai.moveSelection;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface Selects {

  AIChoice selectChoice(Board board, int forPlayer);

}
