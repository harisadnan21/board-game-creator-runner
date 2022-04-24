package oogasalad.engine.model.ai.evaluation.meta;

/**
 * @author Alex Bildner
 */
@FunctionalInterface
public interface Regularizes {

  int regularizeScore(int oldScore);

}
