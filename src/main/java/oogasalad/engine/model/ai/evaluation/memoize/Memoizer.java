package oogasalad.engine.model.ai.evaluation.memoize;

import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.board.Board;

/**
 * @author Alex Bildner
 */
public interface Memoizer {

  Evaluation get(Board board);
}
