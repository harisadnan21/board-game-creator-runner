package oogasalad.engine.model.logicelement.winner;

import oogasalad.engine.model.logicelement.LogicElement;

public abstract class AbstractWinDecision extends LogicElement implements WinDecision {

  protected AbstractWinDecision(int[] parameters) {
    super(parameters);
  }

}
