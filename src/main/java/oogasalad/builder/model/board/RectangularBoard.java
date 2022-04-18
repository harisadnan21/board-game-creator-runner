package oogasalad.builder.model.board;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describes the behavior of a rectangular board.
 *
 * @author Shaan Gondalia
 */
public class RectangularBoard implements Board {

  private static final int EMPTY = -1;
  private static final String WHITE = "0xffffffff";
  public static final String PIECE_CONFIGURATION = "pieceConfiguration";
  public static final String COLOR_CONFIGURATION = "colorConfiguration";
  public static final String HEIGHT = "height";
  public static final String WIDTH = "width";
  public static final String ACTIVE_PLAYER = "activePlayer";
  public static final String SHAPE = "shape";
  public static final String RECTANGLE = "rectangle";
  private int[][] cells;
  private String[][] cellColors;
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
    initializeCells(width, height);
  }

  /**
   * Attempts to place a piece at the given coordinates
   *
   * @param x  the x location to place
   * @param y  the y location to place
   * @param id the name of the piece to place
   */
  public void placePiece(int x, int y, int id) {
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
   * Clears the background of the cell at the given coordinates
   *
   * @param x the x location to clear
   * @param y the y location to clear
   */
  @Override
  public void clearCellBackground(int x, int y) {
    checkInBounds(x, y);
    cellColors[y][x] = WHITE;
  }

  /**
   * Colors the background of the cell at the given coordinates with the given color
   *
   * @param x the x location to color
   * @param y the y location to color
   * @param color the hexadecimal string of the color to set at the cell
   */
  @Override
  public void colorCellBackground(int x, int y, String color) {
    checkInBounds(x, y);
    cellColors[y][x] = color;
  }

  /**
   * Finds the background color of the cell at the given coordinates
   *
   * @param x the x location to query
   * @param y the y location to query
   * @return the background color of the cell at the given coordinates
   */
  @Override
  public String findCellBackground(int x, int y){
    checkInBounds(x, y);
    return cellColors[y][x];
  }

  /**
   * Converts a Board into a String representing the board's JSON Format
   *
   * @return a String representation of the board's JSON Format
   */
  @Override
  public String toJSON() {
    JSONObject obj = new JSONObject();
    obj.put(SHAPE, RECTANGLE);
    obj.put(WIDTH, width);
    obj.put(HEIGHT, height);
    obj.put(ACTIVE_PLAYER, 0); // TODO: Maybe make this an option for the user
    configToJSON(obj);
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
    width = obj.getInt(WIDTH);
    height = obj.getInt(HEIGHT);
    cells = pieceConfigFromJSON(obj.getJSONArray(PIECE_CONFIGURATION));
    cellColors = colorConfigFromJSON(obj.getJSONArray(COLOR_CONFIGURATION));
    return this;
  }

  // Checks whether the requested indices are inbounds
  private void checkInBounds(int x, int y) {
    if (x < 0 || x > width || y < 0 || y > height) {
      throw new IndexOutOfBoundsException();
    }
  }

  // Initializes the cells to be empty
  private void initializeCells(int width, int height) {
    cells = new int[height][width];
    cellColors = new String[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        cells[i][j] = EMPTY;
        cellColors[i][j] = WHITE;
      }
    }
  }

  // Stores the piece and color configurations to a JSON object
  private void configToJSON(JSONObject obj) {
    JSONArray colorConfig = new JSONArray();
    JSONArray pieceConfig = new JSONArray();
    for (int i = 0; i < height; i++) {
      JSONArray pieceRow = new JSONArray();
      JSONArray colorRow = new JSONArray();
      for (int j = 0; j < width; j++) {
        pieceRow.put(cells[i][j]);
        colorRow.put(cellColors[i][j]);
      }
      pieceConfig.put(pieceRow);
      colorConfig.put(colorRow);
    }
    obj.put(PIECE_CONFIGURATION, pieceConfig);
    obj.put(COLOR_CONFIGURATION, colorConfig);
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

  // Converts the piece configuration to a JSON array
  private String[][] colorConfigFromJSON(JSONArray colorConfiguration) {
    String[][] config = new String[height][width];
    for (int i = 0; i < height; i++) {
      JSONArray row = colorConfiguration.getJSONArray(i);
      for (int j = 0; j < width; j++) {
        config[i][j] = row.getString(j);
      }
    }
    return config;
  }
}