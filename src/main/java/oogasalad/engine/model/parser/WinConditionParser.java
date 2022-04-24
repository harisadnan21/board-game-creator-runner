package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import oogasalad.engine.model.logicelement.conditions.Condition;
import oogasalad.engine.model.logicelement.winner.WinDecision;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parser that is responsible for parsing win conditions. This involves parsing conditions and win
 * decisions and assigning them to win conditions accordingly.
 *
 * @author Shaan Gondalia
 */
public class WinConditionParser extends AbstractParser<Collection<EndRule>> {

  private static final String WIN_CONDITIONS = "winDecisions";
  private static final String TYPE = "type";
  private static final String NAME = "name";

  private final ConditionParser conditionParser;

  private static final String DECISION_RESOURCES_PATH = "engine-resources.WinDecisions";
  private static final String REFLECTION_DELIMITER = "\\|";
  private static final String PARAMETER_DELIMITER = "-";
  private static final ResourceBundle DECISION_RESOURCES = ResourceBundle.getBundle(
      DECISION_RESOURCES_PATH);

  /**
   * Creates a new WinConditionParser
   */
  public WinConditionParser() {
    conditionParser = new ConditionParser();
  }

  /**
   * Returns an object that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   * @throws FileNotFoundException if the file is not found
   */
  @Override
  public Collection<EndRule> parse(File configFile) throws FileNotFoundException {
    Collection<EndRule> winConditions = new HashSet<>();
    conditionParser.parse(configFile);
    JSONObject root = fileToJSON(configFile);
    JSONArray winConditionsJSON = root.getJSONArray(WIN_CONDITIONS);
    for (int i = 0; i < winConditionsJSON.length(); i++) {
      JSONObject winCondition = winConditionsJSON.getJSONObject(i);
      Condition[] conditions = conditionParser.resolveConditions(winCondition);
      String name = winCondition.getString(NAME);
      String type = winCondition.getString(TYPE);
      int[] params = paramsToIntArray(winCondition, type);
      WinDecision decision = (WinDecision) getObjectReflection(type, params, DECISION_RESOURCES);
      winConditions.add(new EndRule(name, conditions, decision));
    }
    return winConditions;
  }

  // Converts all required parameters (based on type) of a reference to an ordered integer array
  private int[] paramsToIntArray(JSONObject winCondition, String type) {
    //TODO: Make this line easier to understand
    String[] requiredParams = DECISION_RESOURCES.getString(type)
        .split(REFLECTION_DELIMITER)[1].split(PARAMETER_DELIMITER);
    int[] params = new int[requiredParams.length];
    for (int i = 0; i < requiredParams.length; i++) {
      params[i] = winCondition.getInt(requiredParams[i]);
      //TODO: THIS SUCKS! Standardize how we differentiate (x,y) and (i,j)
      if (requiredParams[i].equals("row") || requiredParams[i].equals("destinationRow") ||
          requiredParams[i].equals("sourceRow")) {
        params[i] *=-1;
      }
    }
    return params;
  }

}
