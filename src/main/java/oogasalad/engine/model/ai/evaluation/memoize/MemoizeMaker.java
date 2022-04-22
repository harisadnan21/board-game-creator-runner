package oogasalad.engine.model.ai.evaluation.memoize;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;

@FunctionalInterface
public interface MemoizeMaker {

  Memoizer getMemoizer(StateEvaluator stateEvaluator);

}
