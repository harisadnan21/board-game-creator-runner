package oogasalad.engine.model.parser;


import java.io.File;
import java.io.FileNotFoundException;
import oogasalad.engine.model.board.Board;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parser that is responsible for parsing the configuration of a board.
 *
 * @author Shaan Gondalia
 */
public class BoardParser extends AbstractParser<Board> {

  /**
   * Creates a board parser
   */
  public BoardParser() {
  }

  /**
   * Reads a board from a configuration file
   *
   * @param configFile the configuration file to read from
   * @return a board with the correct starting configuration
   */
  public Board parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    JSONObject boardObj = findAttribute(root, "board");

    int width = boardObj.getInt("width");
    int height = boardObj.getInt("height");
    JSONArray pieceConfigJSON = boardObj.getJSONArray("pieceConfiguration");
    JSONArray playerConfigJSON = boardObj.getJSONArray("playerConfiguration");
    int[][] pieceConfiguration = intArrayFromJSON(pieceConfigJSON, width, height);
    int[][] playerConfiguration = intArrayFromJSON(playerConfigJSON, width, height);

    return placePieces(width, height, pieceConfiguration, playerConfiguration);
  }

  // Creates a new board with the configurations specified in the arguments
  private Board placePieces(int width, int height, int[][] pieceConfig, int[][] playerConfig) {
    Board board = new Board(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (pieceConfig[i][j] != -1) {
          board.placeNewPiece(i, j, pieceConfig[i][j], playerConfig[i][j]);
        }
      }
    }
    return board;
  }

  // Converts a 2-d JSON array to a 2-d int array with the given dimensions
  private int[][] intArrayFromJSON(JSONArray jsonArray, int width, int height) {
    int[][] arr = new int[height][width];
    for (int i = 0; i < height; i++) {
      JSONArray row = jsonArray.getJSONArray(i);
      for (int j = 0; j < width; j++) {
        int element = row.getInt(j);
        arr[i][j] = element;
      }
    }
    return arr;
  }
}
