package oogasalad.engine.model.ai.evaluation;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import oogasalad.engine.model.board.Board;
import org.jooq.lambda.tuple.Tuple2;

public class Memoize {
  private LoadingCache<Tuple2<Board, Integer>, Integer> memoizer;

  public Memoize(StateEvaluator stateEvaluator) {
    // Resource: https://github.com/ben-manes/caffeine/wiki/Population#loading
    memoizer = Caffeine.newBuilder()
        .maximumSize(1_000)
        .expireAfterWrite(20, TimeUnit.MINUTES)
        .build(key -> stateEvaluator.evaluate(key.v1, key.v2));
  }

  public Integer get(Board board, Integer player) {
    // Resource: https://github.com/ben-manes/caffeine/wiki/Population#loading
    // Lookup and compute an entry if absent, or null if not computable
    return memoizer.get(new Tuple2<>(board, player));
  }

  public Map<Tuple2<Board, Integer>, Integer> getAll(List<Tuple2<Board, Integer>> arguments) {
    // Resource: https://github.com/ben-manes/caffeine/wiki/Population#loading
    // Lookup and compute entries that are absent
    return memoizer.getAll(arguments);
  }

}
