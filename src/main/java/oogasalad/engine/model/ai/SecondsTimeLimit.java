package oogasalad.engine.model.ai;

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
