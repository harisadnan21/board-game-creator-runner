package oogasalad.engine.model.ai.searchTypes.depthlimiting;

public abstract class LimitsDepth {
  private int currentDepth;

  protected LimitsDepth() {
    this.currentDepth = 0;
  }

  public void incrementCurrentDepth() {
    this.currentDepth += 1;
  }

  public abstract boolean shouldContinue();

  public int getCurrentDepth() {
    return currentDepth;
  }
}
