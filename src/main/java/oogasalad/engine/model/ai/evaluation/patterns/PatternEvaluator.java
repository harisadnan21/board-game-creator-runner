package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeMap;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Position;

public abstract class PatternEvaluator implements StateEvaluator {


  //TODO: implement this
  private SortedMap<Position, Set<Pattern>> createIncludes() {
    var includes = TreeMap.empty();
    for(Pattern pattern: this.getPatterns()) {
      for(Position position: pattern.getPositions()) {
        //TODO: finish
      }
    }
    return null;
  }

  public abstract SortedSet<Pattern> getPatterns();

  public abstract SortedMap<Position, Set<Pattern>> getIncludes();



}
