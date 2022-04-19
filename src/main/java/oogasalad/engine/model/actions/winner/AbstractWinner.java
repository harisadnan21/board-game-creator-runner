package oogasalad.engine.model.actions.winner;

import oogasalad.engine.model.board.Board;

public abstract class AbstractWinner implements Winner {

  protected int[] myParameters;

  protected AbstractWinner(int[] parameters) {
    myParameters = parameters;
  }

}
