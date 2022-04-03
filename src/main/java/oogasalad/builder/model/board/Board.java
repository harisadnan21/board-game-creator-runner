package oogasalad.builder.model.board;


import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.model.JSONSerializable;

/**
 * The Board interface describes the API for accessing and modifying the state of the board.
 * Board keeps track of the names and placement of pieces in the board configuration.
 *
 * @author Shaan Gondalia
 */
public interface Board extends JSONSerializable<Board> {

  /**
   * Attempts to place a piece at the given coordinates in the Board
   *
   * @param x the x location to place
   * @param y the y location to place
   * @param name the name of the piece to place
   * @throws OccupiedCellException if the requested indices are already occupied by a piece
   */
  void placePiece(int x, int y, String name) throws OccupiedCellException;

  /**
   * Finds the name of the piece at the given coordinates
   *
   * @param x the x location to query
   * @param y the y location to query
   * @return the name of the piece
   */
  String findPieceAt(int x, int y);

  /**
   * Clears the cell at the given coordinates
   *
   * @param x the x location to clear
   * @param y the y location to clear
   */
  void clearCell(int x, int y);

  /**
   * Converts a Board into a String representing the board's JSON Format
   *
   * @return a String representation of the board's JSON Format
   */
  String toJSON();

  /**
   * Converts a JSON String into a Board
   *
   * @param json the JSON string
   * @return a board of type T made from the JSON string
   */
  Board fromJSON(String json);

}
