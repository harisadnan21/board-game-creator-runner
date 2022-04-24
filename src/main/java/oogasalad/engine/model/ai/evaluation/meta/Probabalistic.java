package oogasalad.engine.model.ai.evaluation.meta;

import java.util.Arrays;
import java.util.Random;
import java.util.SortedMap;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

/**
 * @author Alex Bildner
 */
public class Probabalistic implements StateEvaluator {
  private SortedMap<Double, StateEvaluator> stateEvaluatorMap;
  private Random random;

  public Probabalistic(Tuple2<Double, StateEvaluator>... tuple2s) {
    long numNegative = Arrays.stream(tuple2s).filter(tuple2 -> tuple2.v1() < 0).count();
    if (numNegative > 0) {
      throw new IllegalArgumentException();
    }
    Double total = Seq.seq(Arrays.stream(tuple2s)).foldLeft(0.0, (curSum, tuple2) -> curSum+tuple2.v1());
    if (total != 1) {
      throw new IllegalArgumentException();
    }
    Arrays.stream(tuple2s).forEach(tuple2 -> {
      assert stateEvaluatorMap != null;
      stateEvaluatorMap.put(tuple2.v1, tuple2.v2);
    });
    random = new Random();

  }


  @Override
  public Evaluation evaluate(Board board) {
    double r = random.nextDouble();
    for(var entry: stateEvaluatorMap.entrySet()) {
      if (r < entry.getKey()) {
        return entry.getValue().evaluate(board);
      }
    }
    return stateEvaluatorMap.get(stateEvaluatorMap.lastKey()).evaluate(board);

  }
}
