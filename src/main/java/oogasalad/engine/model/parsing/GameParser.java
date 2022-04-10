package oogasalad.engine.model.parsing;

import java.io.IOException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.actions.Action;
import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.board.ArrayBoard;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.PersistentMapBoard;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.conditions.board_conditions.BoardCondition;
import oogasalad.engine.model.conditions.piece_conditions.PieceCondition;
import oogasalad.engine.model.move.Rule;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameParser {

  //private static Logger log = LoggerFinder.getLoggerFinder().getLogger("Logger", "oogasald.engine");
  public static String CHECKERS_FILE = "data/checkers/checkers_test.json";
  public static String TIC_TAC_TOE_FILE = "data/tictactoe/tictactoe_test.json";
  public static String CONDITION_RESOURCES_PATH = "engine-resources.conditions";
  public static String ACTION_RESOURCES_PATH = "engine-resources.actions";

  public static ResourceBundle CONDITION_RESOURCES = ResourceBundle.getBundle(CONDITION_RESOURCES_PATH);
  public static ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(ACTION_RESOURCES_PATH);


  // https://kodejava.org/how-do-i-read-json-file-using-json-java-org-json-library/

  /**
   * Returns initial board state from given JSON file
   * @param filePath
   * @return
   * @throws IOException
   * @throws OutOfBoardException
   */
  public static Board readInitialBoard(String filePath) throws IOException, OutOfBoardException {
    JSONObject root = getRootObject(filePath);

    JSONObject boardState = root.getJSONObject("board");
    int width = boardState.getInt("width");
    int height = boardState.getInt("height");

    //Board board = new ArrayBoard(height, width);
    Board board = new PersistentMapBoard(height, width);

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

  /**
   * Reads rules from given file
   * @param filePath
   * @return array of rules
   * @throws IOException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public static Rule[] readRules(String filePath)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    JSONObject root = getRootObject(filePath);

    JSONArray rulesJSON = root.getJSONArray("rules");
    int numRules = rulesJSON.length();
    Rule[] rules = new Rule[numRules];
    for (int index = 0; index < numRules; index++) {
      JSONObject ruleJSON = rulesJSON.getJSONObject(index);
      Position repPoint = getRepresentativePoint(ruleJSON.getJSONObject("representativePoint"));
      PieceCondition[] conditions = getConditions(ruleJSON.getJSONArray("conditions"));
      Action[] actions = getActions(ruleJSON.getJSONArray("actions"));
      rules[index] = new Rule(conditions, actions, repPoint.i(), repPoint.j());
    }

    return rules;
  }

  public static WinCondition[] readWinConditions(String filePath)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    JSONObject root = getRootObject(filePath);

    JSONArray winConditionsJSON = root.getJSONArray("winConditions");
    int numConditions = winConditionsJSON.length();
    WinCondition[] winConditions = new WinCondition[numConditions];
    for (int index = 0; index < numConditions; index++) {
      JSONObject winConditionJSON = winConditionsJSON.getJSONObject(index);
      BoardCondition[] conditions = getWinConditions(winConditionJSON.getJSONArray("conditions"));
      Winner winDecision = getwinDecision(winConditionJSON.getJSONArray("winDecision"));
      winConditions[index] = new WinCondition(conditions, winDecision);
    }

    return winConditions;
  }

  /**
   * Reads representative point from a rule given the
   * JSON object for this point
   * @param representativePoint JSON object containing keys "i" and "j"
   * @return
   */
  private static Position getRepresentativePoint(JSONObject representativePoint) {
    int i = representativePoint.getInt("i");
    int j = representativePoint.getInt("j");
    return new Position(i, j);
  }

  /**
   *
   * @param conditionsJSON
   * @return
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  private static PieceCondition[] getConditions(JSONArray conditionsJSON)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    int numConditions = conditionsJSON.length();
    PieceCondition[] conditions = new PieceCondition[numConditions];
    for (int index = 0; index < numConditions; index++) {
      JSONObject conditionJSON = conditionsJSON.getJSONObject(index);
      String name = conditionJSON.getString("name");
      JSONArray paramsJSON = conditionJSON.getJSONArray("parameters");
      int[] parameters = getParameters(paramsJSON);
      try {
        PieceCondition c = (PieceCondition)getActionOrCondition(name, parameters);
        conditions[index] = c;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return conditions;
  }
  private static BoardCondition[] getWinConditions(JSONArray conditionsJSON)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    int numConditions = conditionsJSON.length();
    BoardCondition[] conditions = new BoardCondition[numConditions];
    for (int index = 0; index < numConditions; index++) {
      JSONObject conditionJSON = conditionsJSON.getJSONObject(index);
      String name = conditionJSON.getString("name");
      try {
        BoardCondition c = (BoardCondition)getWinDecisionOrCondition(name);
        conditions[index] = c;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return conditions;
  }

  /**
   * Takes in JSONArray of integers and returns int array
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

  private static Object getWinDecisionOrCondition(String name)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String className = CONDITION_RESOURCES.getString(name);
    Class clazz = Class.forName(className);
    Constructor ctor = clazz.getConstructor();
    Object obj = ctor.newInstance();
    return obj;
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
        Action c = (Action)getActionOrCondition(name, parameters);
        System.out.println(c.getClass());
        actions[index] = c;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return actions;
  }

  private static Winner getwinDecision(JSONArray winDecisionsJSON) {
    JSONObject conditionJSON = winDecisionsJSON.getJSONObject(0);
    String name = conditionJSON.getString("name");
    Winner decision = null;

    try {
      decision = (Winner)getWinDecisionOrCondition(name);
      System.out.println(decision.getClass());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return decision;
  }

  public static JSONObject getRootObject(String filePath) throws IOException {
    String jsonString = Files.readString(Path.of(filePath));
    JSONObject object = new JSONObject(jsonString);
    return object;
  }

  /**
   * Reads a 2D JSONArray of integers
   * @param jsonArray
   * @param array
   */
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
  public static List<WinCondition> getCheckersWinConditions()
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return Arrays.asList(readWinConditions(CHECKERS_FILE));
  }

}
