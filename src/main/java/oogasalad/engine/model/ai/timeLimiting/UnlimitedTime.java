package oogasalad.engine.model.ai.timeLimiting;

import oogasalad.engine.model.ai.timeLimiting.TimeLimit;

public class UnlimitedTime implements TimeLimit {


  @Override
  public void start() {}

  @Override
  public boolean isTimeUp() {
    return false;
  }
}
