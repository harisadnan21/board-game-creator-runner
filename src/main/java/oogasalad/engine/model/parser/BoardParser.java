package oogasalad.engine.model.parser;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.engine.model.board.Board;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parser that is responsible for parsing the configuration of a board.
 *
 * @author Shaan Gondalia
 */
public class BoardParser extends AbstractParser<Board> {

  private final Map<Integer, Integer> pieceMap;

  /**
   * Creates a board parser
   *
   * Board configuration should be read as if the top left
   * piece is also the top left on the board
   */
  public BoardParser() {
    pieceMap = new HashMap<>();
  }

  /**
   * Reads a board from a configuration file
   *
   * @param configFile the configuration file to read from
   * @return a board with the correct starting configuration
   */
  public Board parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    parsePieces(root);
    JSONObject boardObj = findAttribute(root, "board");

    int width = boardObj.getInt("width");
    int height = boardObj.getInt("height");
    JSONArray pieceConfigJSON = boardObj.getJSONArray("pieceConfiguration");
    int[][] pieceConfiguration = intArrayFromJSON(pieceConfigJSON, width, height);

    return placePieces(width, height, pieceConfiguration);
  }

  // Creates a mapping of piece ID to player ID, eliminating the need for a player config array
  private void parsePieces(JSONObject root) {
    JSONArray piecesJSON = root.getJSONArray(PieceParser.PIECES);
    for (int i = 0; i < piecesJSON.length(); i++) {
      JSONObject piece = piecesJSON.getJSONObject(i);
      //TODO: Remove magic values
      //TODO: Add separate piece parser that parses more data about the pieces, such as image
      pieceMap.put(piece.getInt(PieceParser.ID), piece.getInt(PieceParser.PLAYER));
    }
  }

  // Creates a new board with the configurations specified in the arguments
  private Board placePieces(int width, int height, int[][] pieceConfig) {
    Board board = new Board(height, width);
    int boardHeight = board.getHeight();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (pieceConfig[i][j] != -1) {
          // if a piece is at the row i in the array, it should be placed in row height - i -1
          board = board.placeNewPiece(boardHeight - i -1, j, pieceConfig[i][j], pieceMap.get(pieceConfig[i][j]));
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
