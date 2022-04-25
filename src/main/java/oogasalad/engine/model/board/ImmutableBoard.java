package oogasalad.engine.model.board;

import oogasalad.engine.model.board.cells.PositionState;

/**
 * Abstraction for classes that only need to iterate over a given board
 * @see oogasalad.engine.view.game.BoardView
 * @author Robert Cranston
 */
public interface ImmutableBoard extends Iterable<PositionState> {

  /**
   * method to acquire number of rows of board
   *
   * @return
   */
  int getHeight();

  /**
   * Method to acquire number of columns in board
   *
   * @return
   */
  int getWidth();
}
