package oogasalad.engine.model.ai;

import oogasalad.engine.model.board.Board;


/**
 * @author Alex Bildner
 */
public interface AIChoice {

  @Override
  boolean equals(Object obj);

  @Override
  int hashCode();

  Board getResultingBoard(Board board);

  @Deprecated
  Board getResultingBoard();
}
