package oogasalad.engine.model.parsing;

import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.LoggerFinder;
import java.nio.file.Files;
import java.nio.file.Path;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.Condition;
import oogasalad.engine.model.move.Rule;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GameParser {

  //private static Logger log = LoggerFinder.getLoggerFinder().getLogger("Logger", "oogasald.engine");
  public static String CHECKERS_FILE = "data/checkers/checkers.json";
  public static String CONDITION_RESOURCES = "resources/engine-resources/conditions.properties";
  public static String ACTION_RESOURCES = "resources/engine-resources/actions.properties";

  // https://kodejava.org/how-do-i-read-json-file-using-json-java-org-json-library/
  public static Board readInitialBoard(String filePath) throws IOException, OutOfBoardException {
    JSONObject root = getRootObject(filePath);

    JSONObject boardState = root.getJSONObject("board");
    int width = boardState.getInt("width");
    int height = boardState.getInt("height");

    Board board = new Board(height, width);

    int[][] pieceConfiguration = new int[height][width];
    int[][] playerConfiguration = new int[height][width];

    JSONArray pieceConfigJSON = boardState.getJSONArray("pieceConfiguration");
    JSONArray playerConfigJSON = boardState.getJSONArray("playerConfiguration");

    readJSONArrayToIntArray(pieceConfigJSON, pieceConfiguration);
    readJSONArrayToIntArray(playerConfigJSON, playerConfiguration);

    for (int i = 0; i < pieceConfiguration.length; i++) {
      for (int j = 0; j < pieceConfiguration[0].length; j++) {
        if (pieceConfiguration[i][j] != -1) {
          board.placeNewPiece(i,j,pieceConfiguration[i][j], playerConfiguration[i][j]);
        }
      }
    }

    return board;
  }

  public static Rule[] readRules(String filePath) throws IOException {
    JSONObject root = getRootObject(filePath);

    JSONArray rulesJSON = root.getJSONArray("rules");
    int numRules = rulesJSON.length();
    Rule[] rules = new Rule[numRules];
    for (int index = 0; index < numRules; index++) {
      JSONObject ruleJSON = rulesJSON.getJSONObject(index);
      Position repPoint = getRepresentativePoint(ruleJSON.getJSONObject("representativePoint"));
      Condition[] conditions = getConditions(ruleJSON.getJSONArray("conditions"));
      Action[] actions = getActions(ruleJSON.getJSONArray("actions"));
    }

    return null;
  }

  private static Position getRepresentativePoint(JSONObject representativePoint) {
    return null;
  }

  private static Condition[] getConditions(JSONArray conditionsJSON) {
    int numConditions = conditionsJSON.length();
    for (int index = 0; index < numConditions; index++) {
      JSONObject conditionJSON = conditionsJSON.getJSONObject(index);
      String name = conditionJSON.getString("name");
      JSONArray params = conditionJSON.getJSONArray("parameters");
      int paramsSize = params.length();
      int[] parameters = new int[paramsSize];
      for (int j = 0; j < paramsSize; j++) {
        parameters[j] = params.getInt(j);
      }
      // TODO: reflection here
    }
    return null;
  }

  private static Action[] getActions(JSONArray actionsJSON) {
    return null;
  }

  public static JSONObject getRootObject(String filePath) throws IOException {
    String jsonString = Files.readString(Path.of(filePath));
    JSONObject object = new JSONObject(jsonString);
    return object;
  }

  private static void readJSONArrayToIntArray(JSONArray jsonArray, int[][] array) {
    for (int i = 0; i < array.length; i++) {
      JSONArray row = jsonArray.getJSONArray(i);
      for (int j = 0; j < array[0].length; j++) {
        int element = row.getInt(j);
        System.out.printf("%d ", element);
        array[i][j] = element;
      }
      System.out.printf("\n");
    }
  }

  public static void main(String[] args) throws IOException, OutOfBoardException {
    Board board = readInitialBoard(CHECKERS_FILE);
  }

  public static Board getCheckersBoard() throws IOException, OutOfBoardException {
    return readInitialBoard(CHECKERS_FILE);
  }

}
