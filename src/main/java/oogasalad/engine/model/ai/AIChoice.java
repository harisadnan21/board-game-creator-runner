package oogasalad.engine.model.ai;

import oogasalad.engine.model.board.Board;

public interface AIChoice {

  @Override
  boolean equals(Object obj);

  @Override
  int hashCode();

  Board getResultingBoard();



}
