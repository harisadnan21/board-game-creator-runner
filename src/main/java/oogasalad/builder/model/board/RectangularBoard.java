package oogasalad.builder.model.board;

import oogasalad.builder.model.exception.OccupiedCellException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describes the behavior of a rectangular board.
 *
 * @author Shaan Gondalia
 */
public class RectangularBoard implements Board {

  private static final int EMPTY = -1;
  private int[][] cells;
  private int width;
  private int height;

  /**
   * Creates an empty board with the given dimensions
   *
   * @param height the number of rows in the board
   * @param width  the number of cols in the board
   */
  public RectangularBoard(int width, int height) {
    this.width = width;
    this.height = height;
    cells = initializeCells(width, height);
  }

  /**
   * Attempts to place a piece at the given coordinates
   *
   * @param x  the x location to place
   * @param y  the y location to place
   * @param id the name of the piece to place
   * @throws OccupiedCellException if the requested indices are already occupied by a piece
   */
  public void placePiece(int x, int y, int id) throws OccupiedCellException {
    checkInBounds(x, y);
    cells[y][x] = id;
  }

  /**
   * Finds the name of the piece at the given coordinates
   *
   * @param x the x location to query
   * @param y the y location to query
   * @return the name of the piece
   */
  public int findPieceAt(int x, int y) {
    checkInBounds(x, y);
    return cells[y][x];
  }

  /**
   * Clears the cell at the given coordinates
   *
   * @param x the x location to clear
   * @param y the y location to clear
   */
  public void clearCell(int x, int y) {
    checkInBounds(x, y);
    cells[y][x] = EMPTY;
  }

  /**
   * Converts a Board into a String representing the board's JSON Format
   *
   * @return a String representation of the board's JSON Format
   */
  @Override
  public String toJSON() {
    JSONObject obj = new JSONObject();
    // TODO: Remove magic values
    obj.put("shape", "rectangle");
    obj.put("width", width);
    obj.put("height", height);
    obj.put("pieceConfiguration", pieceConfigToJSON());
    obj.put("activePlayer", 0);
    obj.put("background", "games/checkers");
    obj.put("selectionsRequired", true);
    return obj.toString();
  }

  /**
   * Modifies the dimensions and state of the board by loading them in from a JSON string
   *
   * @param json the string containing information about the new board
   * @return a reference to the modified board object
   */
  @Override
  public Board fromJSON(String json) {
    JSONObject obj = new JSONObject(json);
    width = obj.getInt("width");
    height = obj.getInt("height");
    cells = pieceConfigFromJSON(obj.getJSONArray("pieceConfiguration"));
    return this;
  }

  // Checks whether the requested indices are inbounds
  private void checkInBounds(int x, int y) {
    if (x < 0 || x > width || y < 0 || y > height) {
      throw new IndexOutOfBoundsException();
    }
  }

  // Initializes the cells to be empty
  private int[][] initializeCells(int width, int height) {
    int[][] cells = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        cells[i][j] = EMPTY;
      }
    }
    return cells;
  }

  // Converts the piece configuration to a JSON array
  private JSONArray pieceConfigToJSON() {
    JSONArray config = new JSONArray();
    for (int i = 0; i < height; i++) {
      JSONArray row = new JSONArray();
      for (int j = 0; j < width; j++) {
        row.put(cells[i][j]);
      }
      config.put(row);
    }
    return config;
  }

  // Converts the piece configuration to a JSON array
  private int[][] pieceConfigFromJSON(JSONArray pieceConfiguration) {
    int[][] config = new int[height][width];
    for (int i = 0; i < height; i++) {
      JSONArray row = pieceConfiguration.getJSONArray(i);
      for (int j = 0; j < width; j++) {
        config[i][j] = row.getInt(j);
      }
    }
    return config;
  }
}