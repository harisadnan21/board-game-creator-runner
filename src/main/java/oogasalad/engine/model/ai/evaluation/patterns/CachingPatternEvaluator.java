package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import java.util.Collection;
import java.util.concurrent.PriorityBlockingQueue;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class CachingPatternEvaluator extends
    PatternEvaluator {
  protected SortedSet<Pattern> patterns;
  protected SortedMap<Position, Set<Pattern>> includes;
  protected PriorityBlockingQueue<Position> positionsToCheck;

  public CachingPatternEvaluator() {}

  public CachingPatternEvaluator(Collection<Pattern> patterns) {}

  public StateEvaluator withPatterns(Collection<Pattern> patterns) {
    return new CachingPatternEvaluator(patterns);
  }


  @Override
  public Evaluation evaluate(Board board) {
    return null;
  }

  public PriorityBlockingQueue<Position> getPositionsToCheck() {
    return positionsToCheck;
  }

  @Override
  public SortedSet<Pattern> getPatterns() {
    return null;
  }

  @Override
  public SortedMap<Position, Set<Pattern>> getIncludes() {
    return null;
  }
}
