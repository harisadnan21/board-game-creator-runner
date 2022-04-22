package oogasalad.engine.model.ai.evaluation.meta;

@FunctionalInterface
public interface Regularizes {

  int regularizeScore(int oldScore);

}
