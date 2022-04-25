package oogasalad.engine.model.ai.evaluation.meta;

import oogasalad.engine.model.ai.evaluation.StateEvaluator;

/**
 * @author Alex Bildner
 */
public class SeekEquality extends CenterAround {

  public SeekEquality(StateEvaluator stateEvaluator,
      boolean centerPlayerOne, boolean centerPlayerTwo) {
    super(stateEvaluator, centerPlayerOne, centerPlayerTwo, 0);
  }
}
