package oogasalad.engine.model.logicelement.winner;

public abstract class AbstractWinDecision implements WinDecision {

  protected int[] myParameters;

  protected AbstractWinDecision(int[] parameters) {
    myParameters = parameters;
  }

}
