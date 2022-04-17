package oogasalad.engine.model.ai.evaluation.random;

import java.util.Random;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;

public class RandomRange implements StateEvaluator {
  private final int closedMin;
  private final int closedMax;
  private final Random random;


  public RandomRange(int closedMin, int closedMax) {
    this.closedMin = closedMin;
    this.closedMax = closedMax;
    this.random = new Random();
    random.nextInt(closedMin, closedMax+1);
  }

  @Override
  public int evaluate(Board board, int player) {
    return random.nextInt();
  }
}
