package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.SortedSet;
import java.util.concurrent.PriorityBlockingQueue;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class DiffBasedPatternProximityEvaluator extends
    oogasalad.engine.model.ai.evaluation.patterns.PatternProximityEvaluator {
  protected PriorityBlockingQueue<Position> positionsToCheck;

  public DiffBasedPatternProximityEvaluator(SortedSet<oogasalad.engine.model.ai.evaluation.patterns.Pattern> patterns) {
    super(patterns);


  }

  @Override
  public int evaluate(Board board, int player) {
    return 0;
  }

  public PriorityBlockingQueue<Position> getPositionsToCheck() {
    return positionsToCheck;
  }
}
