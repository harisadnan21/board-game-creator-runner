package oogasalad.engine.model.ai.moveSelection;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.board.Board;

/**
 * @author Alex Bildner
 */
@FunctionalInterface
public interface Selects {

  AIChoice selectChoice(Board board, int forPlayer);

}
