package oogasalad.engine.model.ai.timeLimiting;

import oogasalad.engine.model.ai.timeLimiting.TimeLimit;

public class SecondsTimeLimit implements TimeLimit {

  public SecondsTimeLimit(int seconds) {
  }

  @Override
  public void start() {

  }

  @Override
  public boolean isTimeUp() {
    return false;
  }
}
