package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.SortedSet;
import java.util.concurrent.PriorityBlockingQueue;
import oogasalad.engine.model.board.boards.Board;
import oogasalad.engine.model.board.components.Position;

public class DiffBasedPatternProximityEvaluator extends PatternProximityEvaluator {
  protected PriorityBlockingQueue<Position> positionsToCheck;

  public DiffBasedPatternProximityEvaluator(SortedSet<Pattern> patterns) {
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
