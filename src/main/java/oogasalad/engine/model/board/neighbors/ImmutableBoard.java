package oogasalad.engine.model.board.neighbors;

import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.PieceRecord;
import oogasalad.engine.model.board.Position;

/**
 * Immutable Board
 */
public interface ImmutableBoard extends Iterable<Pair<Position, Piece>>{

  int NO_WINNER_YET = -2;

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
  Optional<Piece> getPieceRecord(int i, int j);

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
  oogasalad.engine.model.board.Board deepCopy() throws OutOfBoardException;

  /**
   * Returns the Set of Positions of valid moves of selected piece
   * @return Set of Positions of valid moves of selected piece
   */
  Set<Position> getValidMoves();

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