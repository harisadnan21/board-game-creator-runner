package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class PieceParser extends AbstractParser<Map<Integer, String>>{

  /**
   * Returns a map of piece ids to their image path, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   */
  @Override
  public Map<Integer, String> parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    JSONArray boardObj = root.getJSONArray("pieces");
    return parseInfo(boardObj);
  }

  private Map<Integer, String> parseInfo(JSONArray boardObj) {
    Map<Integer, String> pieces = new HashMap<>();
    for (int i = 0; i < boardObj.length(); i++) {
      JSONObject piece =boardObj.getJSONObject(i);
      pieces.put(piece.getInt("id"), piece.getString("image"));
    }
    return pieces;
  }
}
