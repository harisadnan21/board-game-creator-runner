package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import oogasalad.engine.model.OutOfBoardException;

public interface Board extends Iterable<Pair<Position, Piece>>{
  void placeNewPiece(int row, int column, int type, int player);

  /**
   * Removes piece at (i,j), if there exists a piece
   * If (i,j) out of board bounds, error is thrown
   * @param i row
   * @param j column
   */
  void remove(int i, int j);

  /**
   * Returns true if (i,j) does not contain a piece
   * If (i,j) out of board bounds, error is thrown
   * @param i row
   * @param j column
   * @return
   */
  boolean isEmpty(int i, int j);

  default void throwOutOfBoardError(int i, int j) {
    throw new OutOfBoardException(String.format("Index (%d,%d) out of bounds", i, j));
  }

  /**
   *
   * @param i row
   * @param j column
   * @return An optional which contains a piece record or null
   */
  Optional<PieceRecord> getPieceRecord(int i, int j);


  /**
   * If piece exists at (i1, j1), moves that piece
   * to (i2, j2)
   * @param i1
   * @param j1
   * @param i2
   * @param j2
   * @throws OutOfBoardException
   */
  void move(int i1, int j1, int i2, int j2);

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
  ArrayBoard deepCopy() throws OutOfBoardException;

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

}
