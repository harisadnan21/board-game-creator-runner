package oogasalad.engine.model.ai.enums;

import oogasalad.engine.model.ai.timeLimiting.TimeLimit;
import oogasalad.engine.model.ai.timeLimiting.TimeLimitFactory;

public enum Difficulty {
  RANDOM(1, 3),
  EASY(1, 3),
  MEDIUM(3, 5),
  HARD(5, 8),
  EXPERT(10, 10),
  ADAPTIVE(5, 8);


  private final int depth;

  public TimeLimit timeLimit() {
    return timeLimit;
  }

  private final TimeLimit timeLimit;

  Difficulty(int depth, int seconds) {
    this.depth = depth;
    this.timeLimit = TimeLimitFactory.makeTimeLimit(seconds);
  }

  public int depth() {
    return depth;
  }
}
