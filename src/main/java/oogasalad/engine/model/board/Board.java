package oogasalad.engine.model.board;

import java.util.Optional;
import java.util.Set;
import oogasalad.engine.model.OutOfBoardException;

public interface Board extends Iterable<PositionState>, Cloneable{

  int NO_WINNER_YET = -2;

  /**
   * Places piece on the board at [row,column] with given type and player
   * @param row
   * @param column
   * @param type
   * @param player
   */
  Board placeNewPiece(int row, int column, int type, int player);

  /**
   * Removes piece at (i,j), if there exists a piece
   * If (i,j) out of board bounds, error is thrown
   * @param i row
   * @param j column
   * @return returns modified board (different implementations may return copy or same instance)
   */
  Board remove(int i, int j);

  /**
   * Returns true if (i,j) does not contain a piece
   * If (i,j) out of board bounds, error is thrown
   * @param i row
   * @param j column
   * @return
   */
  boolean isEmpty(int i, int j);

  /**
   * returns true if there is a piece at the given location
   * @param row
   * @param column
   * @return
   */
  boolean hasPieceAtLocation(int row, int column);

  default void throwOutOfBoardError(int i, int j) {
    throw new OutOfBoardException(String.format("Index (%d,%d) out of bounds", i, j));
  }

  /**
   *
   * @param i row
   * @param j column
   * @return the piece at i,j
   */
  Piece getPiece(int i, int j);


  /**
   * If piece exists at (i1, j1), moves that piece
   * to (i2, j2)
   * @param i1
   * @param j1
   * @param i2
   * @param j2
   * @return modified board
   * @throws OutOfBoardException
   */
  Board move(int i1, int j1, int i2, int j2);

  /**
   * Changes turn
   * @param player active player
   */
  void setPlayer(int player);

  /**
   * Returns the player whose turn it is
   * @return active player
   */
  int getPlayer();

  /**
   * returns true if position is in board, false otherwise
   * @param i row
   * @param j column
   * @return
   */
  boolean isValidPosition(int i, int j);

  boolean isValidPosition(Position position);

  /**
   * returns a copy of the board
   * @return
   * @throws OutOfBoardException
   */
  Board copy() throws OutOfBoardException;

  /**
   * Sets the valid moves for the currently selected piece on the board or null if no piece is selected
   * @param validMoves Set of Position values of valid moves for selected cell
   */
  void setValidMoves(Set<Position> validMoves);

  /**
   * Returns the Set of Positions of valid moves of selected piece
   * @return Set of Positions of valid moves of selected piece
   */
  Set<Position> getValidMoves();

  /**
   * Sets the winner of the board. Only called when game is over in checkForWin Method
   * @see oogasalad.engine.model.engine.PieceSelectionEngine
   * @param winner int representing player that wins the game
   */
  void setWinner(int winner);

  /**
   * Returns the winner of the game
   * @see oogasalad.engine.view.BoardView
   * @return winner based on current board
   */
  int getWinner();

  /**
   * Returns number of rows in board
   * @return height
   */
  int getHeight();

  /**
   * Returns number of columns in board
   * @return width
   */
  int getWidth();

  /**
   * Returns PositionState at (i,j)
   * @param i
   * @param j
   * @return
   */
  PositionState getPositionStateAt(int i, int j);
}

