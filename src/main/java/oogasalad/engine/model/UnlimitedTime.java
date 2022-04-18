package oogasalad.engine.model;

public class UnlimitedTime implements TimeLimit {


  @Override
  public void start() {}

  @Override
  public boolean isTimeUp() {
    return false;
  }
}
