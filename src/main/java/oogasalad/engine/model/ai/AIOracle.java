package oogasalad.engine.model.ai;

import java.util.Set;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;

@FunctionalInterface
public interface AIOracle {
  Set<Choice> getChoices(Board board, int player);

}
