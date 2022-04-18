package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;

public class SeekEquality extends CenterAround {

  public SeekEquality(StateEvaluator stateEvaluator,
      boolean centerPlayerOne, boolean centerPlayerTwo) {
    super(stateEvaluator, centerPlayerOne, centerPlayerTwo, 0);
  }
}
