package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Player parser class figures out the number of players in the game
 * by looking at the piece configuration and finding the max player value
 * with the assumption that the number of players is 1 + maxPlayer
 * @author Jake Heller
 */
public class PlayerParser extends AbstractParser<Integer> {

  @Override
  public Integer parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    JSONArray pieces = root.getJSONArray(PieceParser.PIECES);

    // makes the assumption that there is a player from 0 to maxPlayer
    int maxPlayer = -1;
    pieces.length();
    for (int i = 0; i < pieces.length(); i++) {
      int currentPlayer = pieces.getJSONObject(i).getInt(PieceParser.PLAYER);
      if (currentPlayer > maxPlayer) {
        maxPlayer = currentPlayer;
      }
    }
    return maxPlayer + 1;
  }
}
