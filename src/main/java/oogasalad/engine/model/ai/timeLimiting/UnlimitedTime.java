package oogasalad.engine.model.ai.timeLimiting;


/**
 * @author Alex Bildner
 */
public class UnlimitedTime implements TimeLimit {


  @Override
  public void start() {}

  @Override
  public boolean isTimeUp() {
    return false;
  }
}
