package oogasalad.engine.model.parsing;

import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.LoggerFinder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
  public static String CHECKERS_FILE = "data/checkers/enginechecker.json";
  public static String CONDITION_RESOURCES_PATH = "engine-resources.conditions";
  public static String ACTION_RESOURCES_PATH = "engine-resources.actions";

  public static ResourceBundle CONDITION_RESOURCES = ResourceBundle.getBundle(CONDITION_RESOURCES_PATH);
  public static ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(ACTION_RESOURCES_PATH);


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

  public static Rule[] readRules(String filePath)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    JSONObject root = getRootObject(filePath);

    JSONArray rulesJSON = root.getJSONArray("rules");
    int numRules = rulesJSON.length();
    Rule[] rules = new Rule[numRules];
    for (int index = 0; index < numRules; index++) {
      JSONObject ruleJSON = rulesJSON.getJSONObject(index);
      Position repPoint = getRepresentativePoint(ruleJSON.getJSONObject("representativePoint"));
      Condition[] conditions = getConditions(ruleJSON.getJSONArray("conditions"));
      Action[] actions = getActions(ruleJSON.getJSONArray("actions"));
      rules[index] = new Rule(conditions, actions, repPoint.i(), repPoint.j());
    }

    return rules;
  }

  private static Position getRepresentativePoint(JSONObject representativePoint) {
    int i = representativePoint.getInt("i");
    int j = representativePoint.getInt("j");
    return new Position(i, j);
  }

  private static Condition[] getConditions(JSONArray conditionsJSON)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    int numConditions = conditionsJSON.length();
    Condition[] conditions = new Condition[numConditions];
    for (int index = 0; index < numConditions; index++) {
      JSONObject conditionJSON = conditionsJSON.getJSONObject(index);
      String name = conditionJSON.getString("name");
      JSONArray paramsJSON = conditionJSON.getJSONArray("parameters");
      int[] parameters = getParameters(paramsJSON);
      try {
        Condition c = getCondition(name, parameters);
        conditions[index] = c;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return conditions;
  }

  /**
   * takes in JSONArray and returns int array
   * @param parametersJSON
   * @return
   */
  private static int[] getParameters(JSONArray parametersJSON) {
    int paramsSize = parametersJSON.length();
    int[] parameters = new int[paramsSize];
    for (int j = 0; j < paramsSize; j++) {
      parameters[j] = parametersJSON.getInt(j);
    }
    return parameters;
  }

  private static Object getActionOrCondition(String name, int[] parameters)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String className = CONDITION_RESOURCES.getString(name);
    Class clazz = Class.forName(className);
    Constructor ctor = clazz.getConstructor(int[].class);
    Object obj = ctor.newInstance(parameters);
    return obj;
  }

  private static Condition getCondition(String name, int[] parameters)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    return (Condition) getActionOrCondition(name, parameters);
  }

  private static Action getAction(String name, int[] parameters)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    return (Action) getActionOrCondition(name, parameters);
  }

  /**
   * refactor to merge with getConditions
   * @param actionsJSON
   * @return
   */
  private static Action[] getActions(JSONArray actionsJSON) {
    int numActions = actionsJSON.length();
    Action[] actions = new Action[numActions];
    for (int index = 0; index < numActions; index++) {
      JSONObject conditionJSON = actionsJSON.getJSONObject(index);
      String name = conditionJSON.getString("name");
      JSONArray paramsJSON = conditionJSON.getJSONArray("parameters");
      int[] parameters = getParameters(paramsJSON);
      try {
        Action c = getAction(name, parameters);
        System.out.println(c.getClass());
        actions[index] = c;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return actions;
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

  public static void main(String[] args)
      throws IOException, OutOfBoardException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Board board = readInitialBoard(CHECKERS_FILE);
    Rule[] rules = readRules(CHECKERS_FILE);
  }

  public static Board getCheckersBoard() throws IOException, OutOfBoardException {
    return readInitialBoard(CHECKERS_FILE);
  }

  public static List<Rule> getCheckersRules()
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return Arrays.asList(readRules(CHECKERS_FILE));
  }

}
