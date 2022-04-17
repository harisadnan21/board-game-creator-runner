package oogasalad.engine.model.ai;

import oogasalad.engine.model.board.Board;

public abstract class Choice {

  @Override
  public abstract boolean equals(Object obj);

  @Override
  public abstract int hashCode();

  public abstract Board getResultingBoard();



}
