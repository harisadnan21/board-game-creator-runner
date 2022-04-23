package oogasalad.engine.model.ai.evaluation.memoize;

import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.board.Board;

public interface Memoizer {

  Evaluation get(Board board);
}
