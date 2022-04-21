package oogasalad.engine.model.ai.evaluation.random;

import java.util.Random;
import oogasalad.engine.model.ai.evaluation.Evaluation;
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
  }

  @Override
  public Evaluation evaluate(Board board) {
    return new Evaluation(getRandomValue(), getRandomValue());
  }

  private int getRandomValue() {
    return random.nextInt(closedMin, closedMax+1);
  }
}
