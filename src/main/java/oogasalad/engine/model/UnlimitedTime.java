package oogasalad.engine.model;

public class UnlimitedTime implements TimeLimit {


  @Override // Leave empty
  public void start() {}

  @Override // Time never up :)
  public boolean isTimeUp() {
    return false;
  }
}
