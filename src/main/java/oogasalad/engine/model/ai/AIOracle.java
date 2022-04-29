package oogasalad.engine.model.ai;

import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;

/**
 * @author Alex Bildner
 */
public interface AIOracle {
  Stream<Choice> getChoices(Board board, int player);

  boolean isTerminalState(Board board);

}
