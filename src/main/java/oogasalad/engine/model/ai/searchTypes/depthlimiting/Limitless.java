package oogasalad.engine.model.ai.searchTypes.depthlimiting;

public class Limitless extends LimitsDepth {

  public Limitless() {
    super();
  }

  @Override
  public boolean shouldContinue() {
    return true;
  }
}
