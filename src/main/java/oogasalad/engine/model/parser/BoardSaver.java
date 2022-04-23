package oogasalad.engine.model.parser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import oogasalad.engine.model.board.Board;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Saves a board configuration to a File.
 *
 * @author Shaan Gondlaia
 */
public class BoardSaver {

  private static final String PIECE_CONFIGURATION = "pieceConfiguration";
  private static final String ACTIVE_PLAYER = "activePlayer";
  private final File saveFile;

  /**
   * Creates a new BoardSaver that is "attached" to a file
   *
   * @param file the file that the board will eventuall get saved to
   */
  public BoardSaver(File file) {
    saveFile = file;
  }

  /**
   * Saves a board to the file attached to this BoardSaver
   *
   * @param board the board configuration to save
   */
  public void saveBoardConfig(Board board) throws FileNotFoundException {
    InputStream is = new DataInputStream(new FileInputStream(saveFile));
    JSONTokener tokener = new JSONTokener(is);
    JSONObject object = new JSONObject(tokener);
    JSONObject boardObj = object.getJSONObject("board");
    JSONArray pieceConfig = new JSONArray();

    for (int i = 0; i < board.getHeight(); i++) {
      JSONArray pieceRow = new JSONArray();
      for (int j = 0; j < board.getWidth(); j++) {
        pieceRow.put(board.getPiece(i, j).type());
      }
      pieceConfig.put(pieceRow);
    }
    boardObj.put(PIECE_CONFIGURATION, pieceConfig);
    boardObj.put(ACTIVE_PLAYER, board.getPlayer());
    System.out.println(object);
  }
}
