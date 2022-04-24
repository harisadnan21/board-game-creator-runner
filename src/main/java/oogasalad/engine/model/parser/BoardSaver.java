package oogasalad.engine.model.parser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import oogasalad.engine.model.board.Board;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Saves a Board Configuration to a directory. It copies all data from the original configuration,
 * replacing only the board piece configuration and the active player.
 *
 * @author Shaan Gondlaia
 */
public class BoardSaver {

  private static final String PIECE_CONFIGURATION = "pieceConfiguration";
  private static final String ACTIVE_PLAYER = "activePlayer";
  private static final String JSON_FILENAME = "/config.json";
  private static final String RESOURCES_PATH = "/resources/";
  private static final int INDENT_FACTOR = 4;
  private final File startingDirectory;

  /**
   * Creates a new BoardSaver that is "attached" to an initial configuration directory.
   * The board saver will look at the configuration directory, copying this game configuration to a
   * new directory that is supplied in the saveBoardConfig method.
   *
   * @param configDirectory the file that the board will eventually get saved to
   */
  public BoardSaver(File configDirectory) {
    startingDirectory = configDirectory;
  }

  /**
   * Saves a board to the file attached to this BoardSaver
   *
   * TODO: Determine whether images should also be copied in this case (I think they should)
   *
   * @param board the board configuration to save
   * @param newDirectory the new directory to save the configuration to
   */
  public void saveBoardConfig(Board board, File newDirectory) throws IOException {
    JSONObject object = getRootJSON();
    JSONObject boardObj = object.getJSONObject("board");

    JSONArray pieceConfig = pieceConfigToJSON(board);
    boardObj.put(PIECE_CONFIGURATION, pieceConfig);
    boardObj.put(ACTIVE_PLAYER, board.getPlayer());

    copyFiles(newDirectory);

    File saveFile = new File(newDirectory.toString() + JSON_FILENAME);
    FileWriter writer = new FileWriter(saveFile);
    writer.write(object.toString(INDENT_FACTOR));
    writer.close();
  }

  // Copies resource files to new directory. This line can be commented out if it is not needed
  private void copyFiles(File newDirectory) throws IOException {
    File resourceDir = new File(newDirectory, RESOURCES_PATH);
    File startingResourceDir = new File(startingDirectory, RESOURCES_PATH);
    resourceDir.mkdir();
    for (String file : Objects.requireNonNull(startingResourceDir.list())) {
      Files.copy(Path.of(startingResourceDir.toString(), file), Path.of(resourceDir.toString(), file), StandardCopyOption.REPLACE_EXISTING);
    }
  }

  // Gets the root JSON object from the configuration file
  private JSONObject getRootJSON() throws FileNotFoundException {
    File configFile = new File(startingDirectory, JSON_FILENAME);
    InputStream is = new DataInputStream(new FileInputStream(configFile));

    JSONTokener tokener = new JSONTokener(is);
    JSONObject object = new JSONObject(tokener);
    return object;
  }

  // Converts the piece configuration to a JSON array
  private JSONArray pieceConfigToJSON(Board board) {
    JSONArray pieceConfig = new JSONArray();
    for (int i = board.getHeight() - 1; i >= 0; i--) {
      JSONArray pieceRow = new JSONArray();
      for (int j = 0; j < board.getWidth(); j++) {
        pieceRow.put(board.getPiece(i, j).type());
      }
      pieceConfig.put(pieceRow);
    }
    return pieceConfig;
  }

}
