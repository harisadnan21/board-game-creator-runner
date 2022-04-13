package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import java.util.Collection;
import java.util.concurrent.PriorityBlockingQueue;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public abstract class PatternProximityEvaluator implements StateEvaluator {
  protected SortedSet<Pattern> patterns;
  protected SortedMap<Position, Set<Pattern>> includes;

  protected PatternProximityEvaluator(SortedSet<Pattern> patterns) {
    this.patterns = patterns;
    this.includes = this.createIncludes();
  }

  //TODO: implement this
  private SortedMap<Position, Set<Pattern>> createIncludes() {
    return null;
  }

  public SortedSet<Pattern> getPatterns() {
    return patterns;
  }

  public SortedMap<Position, Set<Pattern>> getIncludes() {
    return includes;
  }



}
