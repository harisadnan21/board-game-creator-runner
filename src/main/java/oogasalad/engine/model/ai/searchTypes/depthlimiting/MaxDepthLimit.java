package oogasalad.engine.model.ai.searchTypes.depthlimiting;

public class MaxDepthLimit extends LimitsDepth {
  private final int depthLimit;

  public MaxDepthLimit(int depthLimit) {
    super();
    this.depthLimit = depthLimit;
  }

  @Override
  public boolean shouldContinue () {
    return this.getCurrentDepth() < depthLimit;
  }


  public int getDepthLimit() {
    return depthLimit;
  }




}
