package oogasalad.engine.model.ai.searchTypes;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface Selects {

  AIChoice selectChoice(Board board);

  static void validate(AIOracle aiOracle) {
    if(aiOracle == null) {
      throw new IllegalStateException();
    }

  }
}
