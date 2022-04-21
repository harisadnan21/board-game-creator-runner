package oogasalad.engine.model.actions.winner;

public abstract class AbstractWinDecision implements WinDecision {

  protected int[] myParameters;

  protected AbstractWinDecision(int[] parameters) {
    myParameters = parameters;
  }

}
